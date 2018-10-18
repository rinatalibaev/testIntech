package ru.alibaev.myTest.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.alibaev.myTest.mappers.DocumentsMapper;
import ru.alibaev.myTest.model.Documents;

@RestController
@RequestMapping("/documents")
public class DocumentResources {
	
	private DocumentsMapper mapper;
	
	public DocumentResources(DocumentsMapper mapper) {
		this.mapper = mapper;
	}

	@PostMapping("/updateDocument")
	public List<Documents> updateDocument (@RequestBody Documents doc) {
		Documents document = doc;
		document.setEditTime(LocalDateTime.now());
		mapper.updateDocument(document);
		return mapper.getAllDocuments();
	}
	
	@GetMapping("/getAll")
	public List<Documents> getAll () {
		return mapper.getAllDocuments();
	}
	
	@GetMapping("/getDocById/{id}")
	public Documents getDocumentsById(@PathVariable int id) {
		return mapper.getDocumentsById(id);
	}
	
	@DeleteMapping("/deleteDocument/{id}")
	public List<Documents> deleteDocument (@PathVariable int id) {
		mapper.deleteDocumentById(id);
		return mapper.getAllDocuments();
	}
	
	@PostMapping("/create")
	public List<Documents> createUser (@RequestBody Documents doc) {
		Documents document = doc;
		document.setCreateTime(LocalDateTime.now());
		document.setEditTime(LocalDateTime.now());
		mapper.addDocument(document);
		return mapper.getAllDocuments();
	}
	
	@GetMapping("/findDocument")
	public List<Documents> findDocument (@RequestParam (required = false) String id , @RequestParam (required = false) String title, @RequestParam (required = false) String author) {
		System.out.println("id:" + id + ", " + "title:" + title);
		Documents document = new Documents();
		if (id != null) {
			document.setId(Integer.parseInt(id));
		}
		document.setTitle(title==null ? "" : title);
		document.setAuthor(author==null ? "" : author);
		System.out.println("Id" + document.getId());
		System.out.println("Title" + (title==null ? "" : title));
		System.out.println("Author" + (author==null ? "" : author));
		System.out.println(document);
		System.out.println(mapper.findDocument(document));
		return mapper.findDocument(document);
	}
	
	
}
