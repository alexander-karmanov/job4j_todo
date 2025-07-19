package ru.job4j.todo.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class AuthFilter implements Filter {

    private static final List<String> FILTER =
            List.of("loginPage", "login", "formAddUser", "registration", "fail", "success");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (checkURI(uri)) {
            chain.doFilter(req, res);
            return;
        }

        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean checkURI(String uri) {
        return AuthFilter.FILTER.stream().anyMatch(uri::endsWith);
    }
}
