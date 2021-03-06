package uw.docker.agent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import uw.docker.agent.util.MiscUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SessionException;
import java.io.IOException;


/**
 * 全局异常处理，通过此类捕获全局异常。
 *
 * @author axeon
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * session异常，返回401未登录状态。
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(SessionException.class)
    public void sessionException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    /**
     * 运行时异常，直接打印出来，返回500错。
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(RuntimeException.class)
    public void runtimeException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws IOException {
        log.error(MiscUtils.exceptionToString(e));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * 空指针异常，直接打印出来，返回500错。
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(NullPointerException.class)
    public void nullException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws IOException {
        log.error(MiscUtils.exceptionToString(e));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    /**
     * 其他异常，直接打印出来，返回500错。
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public void unknownException(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws IOException {
        log.error(MiscUtils.exceptionToString(e));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
