package it.academy.web.response;

import java.util.List;

public interface Response {

    String response(List<String> tranposrtList, boolean isValid, String columnName);
}
