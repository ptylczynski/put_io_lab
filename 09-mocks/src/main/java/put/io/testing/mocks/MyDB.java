package put.io.testing.mocks;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;
import java.util.List;

public class MyDB implements IFancyDatabase {

    public Boolean wasConnected;
    public Boolean wasClosed;
    public Boolean wasQuerried;

    @Override
    public void connect() {
        this.wasConnected = true;
    }

    @Override
    public <T> void persist(T t) {

    }

    @Override
    public <T> List<T> queryAll() {
        this.wasQuerried = true;
        return Collections.emptyList();
    }

    @Override
    public void close() {
        this.wasClosed = true;
    }
}
