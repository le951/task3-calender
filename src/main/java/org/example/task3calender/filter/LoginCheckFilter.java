package org.example.task3calender.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.exception.LoginRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;



@Slf4j
public class LoginCheckFilter implements Filter {

    private static final List<Pattern> whiteListPatterns = List.of(
            Pattern.compile("^/login$"),
            Pattern.compile("^/register$")
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        log.info("LoginCheckFilter.doFilter()");

        boolean isWhiteList = whiteListPatterns.stream()
                .anyMatch(p -> p.matcher(httpRequest.getRequestURI()).matches());

        if(!isWhiteList) {
            log.info("!isWhiteList");
            HttpSession session = httpRequest.getSession(false);

            log.info(httpRequest.getRequestedSessionId());

            if(session == null || session.getAttribute("userId") == null){

                // HttpStatus.UNAUTHORIZED.value()와 동일
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
                httpResponse.getWriter().write("{\"message\" : \"로그인이 필요합니다.\"}");

                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
