package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // MVC 방식. 파라미터 받는 예제
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody   // http의 body 부에 이 데이터를 직접 넣어 주겠다.
    // 리턴 시 view를 보내는 게 아니라 데이터를 그대로 보냄
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 이 문자 자체가 데이터로써 넘어감
    }

    // 이번엔 API 방식으로 객체를 전달해 보자
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // 임시로 객체 만듬
    static class Hello {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
