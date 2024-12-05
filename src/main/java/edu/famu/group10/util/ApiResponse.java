package edu.famu.group10.util;



public record ApiResponse<T>(boolean success, String message, Object data, Object error) {

}
