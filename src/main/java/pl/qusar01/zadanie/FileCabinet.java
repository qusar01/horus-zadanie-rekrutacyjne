package pl.qusar01.zadanie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    private Optional<Folder> findFolderByNameHelper(Folder folder, String name) {
        if (folder.getName().equals(name)) {
            return Optional.of(folder);
        }
        if (folder instanceof MultiFolder mf) {
            for (Folder subFolder : mf.getFolders()) {
                Optional<Folder> result = findFolderByNameHelper(subFolder, name);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        for (Folder folder : folders) {
            Optional<Folder> result = findFolderByNameHelper(folder, name);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private void findFoldersBySizeHelper(Folder folder, String size, List<Folder> result) {
        if (folder.getSize().equals(size)) {
            result.add(folder);
        }
        if (folder instanceof MultiFolder mf) {
            for (Folder subFolder : mf.getFolders()) {
                findFoldersBySizeHelper(subFolder, size, result);
            }
        }
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> result = new ArrayList<>();
        for (Folder folder : folders) {
            findFoldersBySizeHelper(folder, size, result);
        }
        return result;
    }

    private int countHelper(Folder folder) {
        int count = 1;
        if (folder instanceof MultiFolder mf) {
            for (Folder subFolder : mf.getFolders()) {
                count += countHelper(subFolder);
            }
        }
        return count;
    }

    @Override
    public int count() {
        int count = 0;
        for (Folder folder : folders) {
            count += countHelper(folder);
        }
        return count;
    }
}
