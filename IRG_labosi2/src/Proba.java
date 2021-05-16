import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Proba {

	public static void main(String[] args) {
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		final GLCanvas glcanvas = new GLCanvas(glcapabilities);
		final JFrame frame = new JFrame("TransformacijaIProjekcija");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowevent) {
				frame.dispose();
				System.exit(0);
			}
		});
		frame.pack();
		frame.getContentPane().add(glcanvas, BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setVisible(true);

	}

}
