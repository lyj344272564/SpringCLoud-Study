package com.richard.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;

@Component
public class BlackListFilter implements GlobalFilter, Ordered {

    private static List<String> blackList = new ArrayList<>();

    static {
        blackList.add("localhost");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String clientIP = request.getRemoteAddress().getHostString();

        if (blackList.contains(clientIP)) {
            // 拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String data = "request be denied";
            DataBuffer warp = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(warp));
        }

        return  chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
