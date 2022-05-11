package view;

import dto.ClassInterfaceDTO;
import dto.PackageDTO;
import dto.ProjectDTO;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;


public class AnalyzerGUI implements View {

    private JButton btnStart;
    private JButton btnStop;
    private JButton btnSave;
    private JTextPane txtPane;

    VisualiserFrame frame;
    private final ViewListener listener;
    private static final int HEIGHT_OFFSET = 30;
    private static final int WIDTH_DIVISOR = 2;

    /**
     * @param listener the listener for the view
     */
    public AnalyzerGUI(ViewListener listener) {
        var h = Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT_OFFSET;
        var w = Toolkit.getDefaultToolkit().getScreenSize().width / WIDTH_DIVISOR;
        frame = new VisualiserFrame(w, h);
        this.listener = listener;
    }

    @Override
    public void setStopEnabled(final Boolean enabled) {
        this.btnStop.setEnabled(enabled);
        this.btnSave.setEnabled(enabled);
    }

    @Override
    public void setStartEnabled(final Boolean enabled) {
        this.btnStart.setEnabled(enabled);
    }

    public <T> void renderTree(T dto) {
        var node = new DefaultMutableTreeNode();
        if (dto instanceof ProjectDTO) node = Trees.createProjectTreeNode((ProjectDTO) dto);
        else if (dto instanceof PackageDTO) node = Trees.createPackageTreeNode((PackageDTO) dto);
        else if (dto instanceof ClassInterfaceDTO) node = Trees.createClassOrInterfaceTreeNode((ClassInterfaceDTO) dto);
        JTree tree = new JTree(new DefaultTreeModel(node));

        JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.validate();
    }

    public void setText(String txt) {
        frame.setText(txt);
    }

    public void addText(String txt) {
        frame.addText(txt);
    }

    private class VisualiserFrame extends JFrame {

        public VisualiserFrame(int w, int h) {
            getContentPane().setLayout(new BorderLayout());
            setTitle(Strings.TITLE);
            setSize(w, h);
            setResizable(true);

            var btnPane = new JPanel();
            btnStart = new JButton(Strings.START);
            btnStop = new JButton(Strings.STOP);
            btnSave = new JButton(Strings.SAVE);
            btnStart.addActionListener(e -> listener.eventPerformed(Commands.START));
            btnStop.addActionListener(e -> listener.eventPerformed(Commands.STOP));
            btnStop.setEnabled(false);
            btnSave.addActionListener(e -> listener.eventPerformed(Commands.SAVE));
            btnSave.setEnabled(false);
            btnPane.add(btnStart);
            btnPane.add(btnStop);
            btnPane.add(btnSave);

            txtPane = new JTextPane();
            txtPane.setEditable(false);
            var txtScrollPane = new JScrollPane(txtPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            DefaultCaret caret = (DefaultCaret) txtPane.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            var txtJPane = new JPanel();
            txtJPane.setLayout(new BorderLayout());
            txtJPane.add(txtScrollPane, BorderLayout.SOUTH);

            getContentPane().add(btnPane, BorderLayout.NORTH);
            getContentPane().add(txtJPane, BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setVisible(true);
        }

        void setText(final String text) {
            txtPane.setText(text);
        }

        void addText(final String text) {
            txtPane.setText(txtPane.getText() + "\n" + text);
        }

    }

}
