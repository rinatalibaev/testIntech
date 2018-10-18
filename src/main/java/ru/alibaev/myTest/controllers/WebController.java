package ru.alibaev.myTest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class WebController {
	
    @GetMapping(value= {"/"})
    public String homepage(){
        return "index";
    }

    @GetMapping(value="/documents")
    public String docPage(){
        return "documents";
    }
    
    @PostMapping(value="/documents")
    public String docPagePost(){
        return "documents";
    }
    
    @GetMapping(value="/showdocument")
    public String docSinglePage(){
        return "showdocument";
    }
    
    @GetMapping(value="/editDocument")
   	public String editDocument(
   			@RequestParam(name="id", required=true) String id, 
   			@RequestParam(name="title", required=true) String title,
   			@RequestParam(name="author", required=true) String author, 
   			@RequestParam(name="src", required=true) String src,
   			@RequestParam(name="createTime", required=true) String createTime, 
   			@RequestParam(name="editTime", required=true) String editTime, 
   			Model model) {
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("src", src);
        model.addAttribute("createTime", createTime);
        model.addAttribute("editTime", editTime);
    	return "editdocument";
    }
    
    @GetMapping(value="/addDocument")
    public String addDocument () {
    	return "adddocument";
    }
    
    @GetMapping(value="/findForm")
    public String findDocument () {
    	return "findform";
    }
    
    @GetMapping(value="/confirmdialog")
    public String confirmDialog () {
    	return "confirmdialog";
    }
    
}
