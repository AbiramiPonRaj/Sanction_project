package com.ponsun.san.infrastructure.utils;

import java.util.UUID;

public class Response {

    public Object id;

    public Response() {

    }
    public Response(Object id) {
        this.id = id;
    }
    public static Response of(Integer id) {
        return new Response(id);
    }
    public static Response of(Long id) {
        return new Response(id);
    }
    public static Response of(UUID id) {
        return new Response(id);
    }
    public static Response empty() {
        return new Response(0L);
    }
    public Object getId() {
        return id;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append("id:"+id).append("}");
        return sb.toString();
    }

}
