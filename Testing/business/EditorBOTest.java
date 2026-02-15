package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bll.EditorBO;
import dal.IFacadeDAO;
import dto.Documents;

public class EditorBOTest {

    private IFacadeDAO mockDAO;
    private EditorBO editorBO;

    @BeforeEach
    void setUp() {
        mockDAO = Mockito.mock(IFacadeDAO.class);
        editorBO = new EditorBO(mockDAO);
    }

    @Test
    void testSearchKeyword() {
        Documents doc1 = new Documents(1, "file1.txt", "Hello world example", null, null, null);
        Documents doc2 = new Documents(2, "file2.txt", "Java testing example", null, null, null);

        when(mockDAO.getFilesFromDB()).thenReturn(Arrays.asList(doc1, doc2));

        List<String> results = editorBO.searchKeyword("example");

        assertNotNull(results);
        assertEquals(2, results.size(), "Both documents should match keyword 'example'");
    }

    @Test
    void testTransliterate() {
        when(mockDAO.transliterateInDB(1, "سلام")).thenReturn("salam");

        String result = editorBO.transliterate(1, "سلام");

        assertEquals("salam", result, "Transliteration should return mocked value 'salam'");
    }

    @Test
    void testPerformTFIDF() {
        List<String> unSelectedDocs = Arrays.asList("cat dog", "dog mouse");
        String selectedDoc = "cat mouse";

        when(mockDAO.performTFIDF(unSelectedDocs, selectedDoc)).thenReturn(0.75);

        double score = editorBO.performTFIDF(unSelectedDocs, selectedDoc);

        assertEquals(0.75, score, 0.001, "TF-IDF score should match mocked value 0.75");
    }
}