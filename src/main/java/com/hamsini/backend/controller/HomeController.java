package com.hamsini.backend.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hamsini.backend.model.EnrollRequest;
import com.hamsini.backend.repository.EnrollRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
  @GetMapping("/")
    public String homePage() {
        // This returns index.html from templates/
        return "index";
    }

    @GetMapping("/blogs")
    public String blogsPage() {
        return "blogs"; // loads templates/blogs.html
    }

    @GetMapping("/newBatches")
    public String newBatchesPage() {
        return "newBatches"; // loads templates/newBatches.html
    }

    @GetMapping("/hire")
    public String hirePage() {
        return "hire"; // loads templates/hire.html
    }

    @GetMapping("/online")
    public String onlinePage() {
        return "online"; // loads templates/online.html
    }
    @GetMapping("/offline")
    public String offlinePage() {
        return "offline"; // loads templates/offline.html
    }
    @GetMapping("/assistance")
    public String assistancePage() {
        return "assistance"; // loads templates/assistance.html
    }
    @GetMapping("/certification")
    public String certificationPage() {
        return "certification"; // loads templates/certification.html
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // loads templates/about.html
    }
    @GetMapping("/contact")
    public String contactPage() {
        return "contact"; // loads templates/contact.html
    }

    @GetMapping("/fullStack")
    public String fullStackPage() {
        return "fullStack"; // loads templates/fullStack.html
    }
    @GetMapping("/awsDevops")
    public String awsDevopsPage() {
        return "awsDevops"; // loads templates/awsDevops.html
    }
    @GetMapping("/azureDevops")
    public String azureDevopsPage() {
        return "azureDevops"; // loads templates/azureDevops.html
    }
    @GetMapping("/dataAnalytics")
    public String dataAnalyticsPage() {
        return "dataAnalytics"; // loads templates/dataAnalytics.html
    }
    @GetMapping("/dataScience")
    public String dataSciencePage() {
        return "dataScience"; // loads templates/dataScience.html
    }
    @GetMapping("/gen")
    public String genPage() {
        return "gen"; // loads templates/gen.html
    }
    @GetMapping("/powerBi")
    public String powerBiPage() {
        return "powerBi"; // loads templates/powerBi.html
    }
    @GetMapping("/java")
    public String javaPage() {
        return "java"; // loads templates/java.html
    }
    @GetMapping("/react")
    public String reactPage() {
        return "react"; // loads templates/react.html
    }
    @GetMapping("/python")
    public String pythonPage() {
        return "python"; // loads templates/python.html
    }

    @GetMapping("/mernStack")
    public String mernStackPage() {
        return "mernStack"; // loads templates/mernStack.html
    }


    

    @Autowired
    private EnrollRepository enrollRepo;

    // Show Admin Login Page
    @GetMapping("/adminlogin")
    public String showAdminLogin() {
        return "adminlogin";  
    }

    // Handle Login
    @PostMapping("/adminlogin")
    public String doAdminLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (email.equals("admin@gmail.com") && password.equals("admin123")) {
            session.setAttribute("admin", true);
            model.addAttribute("enrollments", enrollRepo.findAll());
            return "admin-dashboard";  
        } else {
            model.addAttribute("error", "Invalid Email or Password");
            return "adminlogin";
        }
    }

    // Admin Dashboard
    @GetMapping("/admin-dashboard")
    public String showDashboard(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/adminlogin"; // secure dashboard
        }
        model.addAttribute("enrollments", enrollRepo.findAll());
        return "admin-dashboard";
    }

    // Logout
    @GetMapping("/adminlogout")
    public String adminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/adminlogin";
    }

    //Download Export
    
    @GetMapping("/admin/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=enrollments.xlsx");

        List<EnrollRequest> enrollments = enrollRepo.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Enrollments");

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Full Name", "Mobile", "Email", "Course", "Batch"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Data rows
        int rowIdx = 1;
        for (EnrollRequest enroll : enrollments) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(enroll.getId());
            row.createCell(1).setCellValue(enroll.getFullName());
            row.createCell(2).setCellValue(enroll.getMobile());
            row.createCell(3).setCellValue(enroll.getEmail());
            row.createCell(4).setCellValue(enroll.getCourse());
            row.createCell(5).setCellValue(enroll.getBatch());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}







   





