package pl.qusar01.zadanie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileCabinetTest {

    private FileCabinet cabinet;

    @BeforeEach
    void setUp() {
        FolderImpl f1 = new FolderImpl("Docs", "SMALL");
        FolderImpl f2 = new FolderImpl("Music", "LARGE");
        MultiFolderImpl f3 = new MultiFolderImpl("Homework", "LARGE", new ArrayList<>());
        f3.add(new FolderImpl("Maths", "MEDIUM"));
        f3.add(new FolderImpl("Physics", "LARGE"));

        cabinet = new FileCabinet(List.of(f1, f2, f3));
    }

    @Test
    void testFindFolderByNameExisting() {
        Optional<Folder> found = cabinet.findFolderByName("Physics");
        assertTrue(found.isPresent());
        assertEquals("Physics", found.get().getName());
    }

    @Test
    void testFindFolderByNameNotExisting() {
        Optional<Folder> found = cabinet.findFolderByName("asd");
        assertTrue(found.isEmpty());
    }

    @Test
    void testFindFoldersBySize() {
        List<Folder> result = cabinet.findFoldersBySize("LARGE");
        assertEquals(3, result.size());
        assertEquals("Music", result.get(0).getName());
    }

    @Test
    void testCount() {
        assertEquals(5, cabinet.count());
    }
}

