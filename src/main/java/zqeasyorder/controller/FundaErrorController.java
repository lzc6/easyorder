package zqeasyorder.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zqeasyorder.domain.ResultMap;


@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class FundaErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    @ResponseBody
    public ResultMap doHandleError() {
        return new ResultMap(0,"系统异常！",false,"","");
    }
}