package com.nodemules.api.potion.core.auth;

import com.nodemules.api.potion.core.auth.model.LoginAttempt;
import com.nodemules.api.potion.persistence.domain.LoginBlacklist;
import com.nodemules.api.potion.persistence.domain.LoginSuccess;
import com.nodemules.api.potion.persistence.repository.LoginAttemptRepository;
import com.nodemules.api.potion.persistence.repository.LoginBlacklistRepository;
import com.nodemules.api.potion.persistence.repository.LoginSuccessRepository;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brent
 * @since 12/15/17.
 */
@Slf4j
@Service
public class LoginAttemptService implements LoginAttemptOperations {

  private static final int MAX_ATTEMPTS = 5;
  private static final int EXPIRE_TIME_DURATION_MINUTES = 5;
  private static final int EXPIRE_TIME_DURATION = EXPIRE_TIME_DURATION_MINUTES * 60 * 1000;
  private static final int BLACKLIST_WINDOW_MINUTES = 5;
  private static final int BLACKLIST_WINDOW = BLACKLIST_WINDOW_MINUTES * 60 * 1000;

  private HttpServletRequest request;
  private LoginAttemptRepository loginAttemptRepo;
  private LoginBlacklistRepository loginBlacklistRepo;
  private LoginSuccessRepository loginSuccessRepo;
  private LoginAttemptMapper mapper;

  @Autowired
  public LoginAttemptService(HttpServletRequest request,
      LoginAttemptRepository loginAttemptRepo, LoginBlacklistRepository loginBlacklistRepo,
      LoginSuccessRepository loginSuccessRepo,
      LoginAttemptMapper mapper) {
    this.request = request;
    this.loginAttemptRepo = loginAttemptRepo;
    this.loginBlacklistRepo = loginBlacklistRepo;
    this.loginSuccessRepo = loginSuccessRepo;
    this.mapper = mapper;
  }

  @Override
  public void loginSuccess(Long userId) {

    String ipAddress = HttpRequestUtil.getIpAddress(request);
    String userAgent = HttpRequestUtil.getUserAgent(request);

    LoginSuccess success = mapper.toSuccessEntity(new LoginAttempt(userId, ipAddress, new Date(), userAgent));
    success.setLoginTime(new Date());

    loginSuccessRepo.save(success);
  }

  @Override
  public void loginFailed(Long userId) {
    String ipAddress = HttpRequestUtil.getIpAddress(request);
    String userAgent = HttpRequestUtil.getUserAgent(request);

    LoginAttempt attempt = new LoginAttempt(userId, ipAddress, new Date(), userAgent);

    loginAttemptRepo.save(mapper.toAttemptEntity(attempt));

    Date blacklistIntervalCheck = new Date(new Date().getTime() - BLACKLIST_WINDOW);

    int attempts = loginAttemptRepo
        .countByUserUserIdAndIpAddressAndAttemptTimeAfter(userId, ipAddress,
            blacklistIntervalCheck);

    if (attempts > MAX_ATTEMPTS) {
      loginAttemptLimitExceeded(attempt);
    }

  }

  @Override
  public void checkBlacklist(Long userId) {
    String ipAddress = HttpRequestUtil.getIpAddress(request);

    LoginBlacklist blacklist = loginBlacklistRepo
        .findTopByUserUserIdAndIpAddressOrderByExpireTimeDesc(userId, ipAddress);

    if (blacklist != null && blacklist.getExpireTime().after(new Date())) {
      throw new LoginAttemptExceededException(
          "Number of login attempts exceeded, try again at: " + blacklist.getExpireTime());
    }
  }

  private void loginAttemptLimitExceeded(LoginAttempt attempt) {
    LoginBlacklist blacklist = mapper.toBlacklistEntity(attempt);
    blacklist.setExpireTime(new Date(blacklist.getAttemptTime().getTime() + EXPIRE_TIME_DURATION));
    loginBlacklistRepo.save(blacklist);
    throw new LoginAttemptExceededException(
        "Number of login attempts exceeded, try again at: " + blacklist.getExpireTime());
  }
}
