import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class LengthRestrictedDocument extends PlainDocument {
	private static final long serialVersionUID = 1L;
	private final int limit;

	public LengthRestrictedDocument(int limit) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, a);
		}
	}
}