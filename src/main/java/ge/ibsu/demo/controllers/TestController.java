package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.Test;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/test")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = {"application/json"})
    public String test() {
        return "Hello";
    }

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST, produces = {"application/json"})
    public Test testPost(@RequestBody Test rd) {
        rd.setLastName(rd.getLastName() + "Response");
        rd.setAge(20);
        return rd;
    }



}
