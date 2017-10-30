
import java.util.List;

/**
 * @author Jaylin
 */
public class SyncNoteResponse extends BaseResponse {

    List<Note> noteList;

    public SyncNoteResponse(int code, String message) {
        super(code, message);
    }

    public SyncNoteResponse(int code, String message, List<Note> noteList) {
        super(code, message);
        this.noteList = noteList;
    }
}
