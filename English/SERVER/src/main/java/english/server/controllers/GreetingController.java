package english.server.controllers;

import english.server.model.*;
import english.server.repositories.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AllRoleRepository allRoleRepository;

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/login")
    public String login(){
        return "top_login";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }



    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/registration/student")
    public String registrationStudent() {
        return "registrationForStudent";
    }

    @PostMapping("/registration/student")
    public String doregistrationStudent(@RequestParam String name, @RequestParam String password, @RequestParam String link, @RequestParam String login, @RequestParam String surname) {
        AllRole allroles=allRoleRepository.findByUserlogin(login);

        if(allroles!=null)
        {
            return "registrationForStudent";
        }

        else {
            Student new_student = new Student();
            AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_STUDENT";

            new_student.name = name;
            new_student.link = link;
            new_student.login = login;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_student.password = passwordEncoder.encode(password);
            new_student.surname = surname;
            new_student.tariff = "default";
            new_student.yourTeacher = "default";
            new_student.type="ROLE_STUDENT";

            ResponseEntity.ok(allRoleRepository.save(new_allrole));
            ResponseEntity.ok(studentRepository.save(new_student));
            return "redirect:/login";}
    }

    @GetMapping("/registration/teacher")
    public String registrationTeacher() {
        return "registrationForTeacher";
    }

    @PostMapping("/registration/teacher")
    public String doregistration_new_teacher(@RequestParam String name, @RequestParam String password, @RequestParam String link, @RequestParam String login, @RequestParam String education, @RequestParam String surname) {
        AllRole allrole=allRoleRepository.findByUserlogin(login);
        if(allrole!=null)
        {return "registrationForTeacher";}
        else
        {AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_TEACHER";
            Teacher new_teacher = new Teacher();
            new_teacher.name = name;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_teacher.password_hash = passwordEncoder.encode(password);
            new_teacher.link = link;
            new_teacher.login = login;
            new_teacher.education = education;
            new_teacher.surname = surname;
            new_teacher.type="ROLE_TEACHER";

            ResponseEntity.ok(allRoleRepository.save(new_allrole));
            ResponseEntity.ok(teacherRepository.save(new_teacher));
            return "redirect:/login";
        }
    }

    @GetMapping("/registration/admin")
    public String registrationAdmin() {
        return "registrationForAdmin";
    }

    @PostMapping("/registration/admin")
    public String doregistration_new_admin(@RequestParam String name, @RequestParam String password, @RequestParam String link, @RequestParam String login, @RequestParam String position, @RequestParam String surname) {
        AllRole allrole=allRoleRepository.findByUserlogin(login);
        if(allrole!=null)
        {return "registrationForAdmin";}
        else
        {AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_ADMIN";
            Admin new_admin = new Admin();
            new_admin.name = name;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_admin.password_hash = passwordEncoder.encode(password);
            new_admin.link = link;
            new_admin.login = login;
            new_admin.position = position;
            new_admin.surname = surname;
            new_admin.type="ROLE_ADMIN";

            ResponseEntity.ok(allRoleRepository.save(new_allrole));
            ResponseEntity.ok(adminRepository.save(new_admin));
            return "redirect:/login";
        }
    }


    @GetMapping("/home")
    public String home(@RequestParam(name = "type",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();

        if(allRoleRepository.findByUserlogin(auth1.getName()).type.equals("ROLE_STUDENT"))
        {
            model.addAttribute("type","/student_page");

        }
        else if(allRoleRepository.findByUserlogin(auth1.getName()).type.equals("ROLE_TEACHER"))
        {model.addAttribute("type","/teacher_page");}
        else{
            model.addAttribute("type","/admin_page");
        }

        return "main_page";
    }
    @GetMapping("/pay/begginer")
    public String pay_begginer() {
        return "pay_begginer";
    }

    @PostMapping("/pay/begginer")
    public String pay_beginner_2(@RequestParam String tariff,@RequestParam String number) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(auth1.getName()).tariff=tariff;
        studentRepository.save(studentRepository.findByStudentlogin(auth1.getName()));
        return "redirect:/home";
    }

    @GetMapping("/pay/elementary")
    public String pay_elementary() {
        return "pay_elementary";
    }

    @PostMapping("/pay/elementary")
    public String pay_elementary_2(@RequestParam String tariff,@RequestParam String number) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(auth1.getName()).tariff=tariff;
        studentRepository.save(studentRepository.findByStudentlogin(auth1.getName()));
        return "redirect:/home";
    }

    @GetMapping("/pay/intermediate")
    public String pay_intermediate() {
        return "pay_intermediate";
    }

    @PostMapping("/pay/intermediate")
    public String pay_intermediate_2(@RequestParam String tariff,@RequestParam String number) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(auth1.getName()).tariff=tariff;
        studentRepository.save(studentRepository.findByStudentlogin(auth1.getName()));
        return "redirect:/home";
    }

    @GetMapping("/pay/upper_intermediate")
    public String pay_upper_intermediate() {
        return "pay_upper_intermediate";
    }

    @PostMapping("/pay/upper_intermediate")
    public String pay_upper_intermediate_2(@RequestParam String tariff,@RequestParam String number) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(auth1.getName()).tariff=tariff;
        studentRepository.save(studentRepository.findByStudentlogin(auth1.getName()));
        return "redirect:/home";
    }

    @GetMapping("/pay/advanced")
    public String pay_advanced() {
        return "pay_advanced";
    }

    @PostMapping("/pay/advanced")
    public String pay_advanced_2(@RequestParam String tariff,@RequestParam String number) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(auth1.getName()).tariff=tariff;
        studentRepository.save(studentRepository.findByStudentlogin(auth1.getName()));
        return "redirect:/home";
    }
    @GetMapping("/student_page")
    public String studentPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",studentRepository.findByStudentlogin(auth1.getName()).name);
        model.addAttribute("surname",studentRepository.findByStudentlogin(auth1.getName()).surname);
        model.addAttribute("login",studentRepository.findByStudentlogin(auth1.getName()).login);
        model.addAttribute("link",studentRepository.findByStudentlogin(auth1.getName()).link);
        model.addAttribute("tariff",studentRepository.findByStudentlogin(auth1.getName()).tariff);
        model.addAttribute("teacher",studentRepository.findByStudentlogin(auth1.getName()).yourTeacher);
        return "student_page";
    }
    @GetMapping("/teacher_page")
    public String teacherPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",teacherRepository.findByTeacherlogin(auth1.getName()).name);
        model.addAttribute("surname",teacherRepository.findByTeacherlogin(auth1.getName()).surname);
        model.addAttribute("login",teacherRepository.findByTeacherlogin(auth1.getName()).login);
        model.addAttribute("link",teacherRepository.findByTeacherlogin(auth1.getName()).link);
        model.addAttribute("education",teacherRepository.findByTeacherlogin(auth1.getName()).education);
        return "teacher_page";
    }

    @GetMapping("/admin_page")
    public String adminPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",adminRepository.findByAdminlogin(auth1.getName()).name);
        model.addAttribute("surname",adminRepository.findByAdminlogin(auth1.getName()).surname);
        model.addAttribute("login",adminRepository.findByAdminlogin(auth1.getName()).login);
        model.addAttribute("link",adminRepository.findByAdminlogin(auth1.getName()).link);
        model.addAttribute("position",adminRepository.findByAdminlogin(auth1.getName()).position);
        return "admin_page";
    }

   @GetMapping("/my_student")
   public String getStudent(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
       Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
       model.addAttribute("student", studentRepository.findByStudentTeacher(auth1.getName()));
       return "my_student";
   }

    @GetMapping("/free_student")
    public String freeStudent(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("student", studentRepository.findFreeStudent());
        return "free_student";
    }


    @GetMapping("/target")
    public String target() {
        return "target";
    }

    @PostMapping("/target")
    public String target(@RequestParam String yourTeacher,@RequestParam String student) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        studentRepository.findByStudentlogin(student).yourTeacher=yourTeacher;
        studentRepository.save(studentRepository.findByStudentlogin(student));
        return "redirect:/home";
    }




}
