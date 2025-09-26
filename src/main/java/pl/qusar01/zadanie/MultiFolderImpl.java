package pl.qusar01.zadanie;

import java.util.List;

public class MultiFolderImpl implements MultiFolder{
    private final String name;
    private final String size;
    private final List<Folder> folders;

    public MultiFolderImpl(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }

    public void add(Folder folder) {
        folders.add(folder);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public List<Folder> getFolders() {
        return List.copyOf(folders);
    }
}
