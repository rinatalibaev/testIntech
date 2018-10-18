package ru.alibaev.myTest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.plugin.Intercepts;

import ru.alibaev.myTest.model.Documents;

@Mapper
public interface DocumentsMapper {

	@Select("select * from documents")
	List<Documents> getAllDocuments();
	
	@Insert("INSERT into documents (title, author, src, createTime, editTime) VALUES(#{title}, #{author}, #{src}, #{createTime}, #{editTime})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
				before = false, resultType = Integer.class)
	void addDocument(Documents document);

	@Select("SELECT id, title, author, src, createTime, editTime from documents where id = #{id}")
	Documents getDocumentsById(int id);

	@Update("UPDATE documents SET title = #{title}, author = #{author}, src = #{src} WHERE id =#{id}")
	void updateDocument(Documents document);

	@Delete("DELETE FROM documents WHERE id = #{id}")
	void deleteDocumentById(int id);

	
	@Select("SELECT id, title, author, src, createTime, editTime from documents "
			+ "where ((id=#{id}) OR (#{id} = 0)) AND "
			+ "((title = #{title}) OR (#{title} = '')) AND "
			+ "((author = #{author}) OR (#{author} = ''))")
	List<Documents> findDocument(Documents document);
}

