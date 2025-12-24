//package com.example.resumematcher.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.resumematcher.model.candiate;
//import com.example.resumematcher.model.job;
//import com.example.resumematcher.util.EducationValidator;
//
//@RestController
//public class EducationController {
//
//    @GetMapping("/checkEligibility")
//    public boolean checkEligibility() {
//
//        candiate candidate = new candiate();
//        candidate.setTenthScore(86.6);
//        candidate.setTwelfthScore(90.683);
//        candidate.setUgScore(8.5);
//        candidate.setTenthPassoutYear(2020);
//        candidate.setTwelfthPassoutYear(2022);
//        candidate.setUgPassoutYear(2025);
//
//        job job = new job();
//        job.setTenthRequirement(80.0);
//        job.setTwelfthRequirement(75.0);
//        job.setUgRequirement(8.0);
//        job.setTenthPassoutYear(2020);
//        job.setTwelfthPassoutYear(2022);
//        job.setUgPassoutYear(2025);
//
//        return EducationValidator.isEligible(candidate, job);
//    }
//}
//package com.example.resumematcher.controller;
//
//import org.springframework.web.bind.annotation.*;
//
//import com.example.resumematcher.model.candiate;
//import com.example.resumematcher.model.job;
//import com.example.resumematcher.util.EducationValidator;
//
//@RestController
//@RequestMapping("/api")
//public class EducationController {
//
//    @PostMapping("/checkEligibility")
//    public boolean checkEligibility(
//            @RequestParam double tenthScore,
//            @RequestParam double twelfthScore,
//            @RequestParam double ugScore,
//            @RequestParam int tenthPassoutYear,
//            @RequestParam int twelfthPassoutYear,
//            @RequestParam int ugPassoutYear,
//
//            @RequestParam double tenthRequirement,
//            @RequestParam double twelfthRequirement,
//            @RequestParam double ugRequirement,
//            @RequestParam int jobTenthPassoutYear,
//            @RequestParam int jobTwelfthPassoutYear,
//            @RequestParam int jobUgPassoutYear
//    ) {
//
//        candiate candidate = new candiate();
//        candidate.setTenthScore(tenthScore);
//        candidate.setTwelfthScore(twelfthScore);
//        candidate.setUgScore(ugScore);
//        candidate.setTenthPassoutYear(tenthPassoutYear);
//        candidate.setTwelfthPassoutYear(twelfthPassoutYear);
//        candidate.setUgPassoutYear(ugPassoutYear);
//
//        job job = new job();
//        job.setTenthRequirement(tenthRequirement);
//        job.setTwelfthRequirement(twelfthRequirement);
//        job.setUgRequirement(ugRequirement);
//        job.setTenthPassoutYear(jobTenthPassoutYear);
//        job.setTwelfthPassoutYear(jobTwelfthPassoutYear);
//        job.setUgPassoutYear(jobUgPassoutYear);
//
//        return EducationValidator.isEligible(candidate, job);
//    }
//}
