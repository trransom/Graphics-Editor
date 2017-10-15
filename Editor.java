package editor;

import graphics.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * Creates a program where you can add and manipulate various objects.
 *
 * @author Thomas Ransom
 */
public class Editor extends JFrame implements MouseListener, MouseMotionListener {

    private Color lc = Color.black;
    private Color fc = Color.black;
    private Shape currentShape;
    private Canvas center;
    private JComboBox shapeBox;
    private Shapes currentShapeType;
    private JLabel status;
    private final int XOFF = 8;
    private final int YOFF = 31;
    private JCheckBox filledCheckbox;

    public Editor() {
        super("Editor");
        init();
    }

    public void init() {
        setLayout(new BorderLayout());
        center = new Canvas();
        center.setBackground(Color.white);
        add(center, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        shapeBox = new JComboBox(Shapes.values());
        shapeBox.addActionListener((ActionEvent ae) -> currentShapeType = (Shapes) shapeBox.getSelectedItem());
        currentShapeType = Shapes.LINE;
        controlPanel.add(shapeBox);
        JButton colorButton = new JButton("Fill Color");
        status = new JLabel("");
        add(status, BorderLayout.SOUTH);
        colorButton.addActionListener((ActionEvent a) -> {

            fc = JColorChooser.showDialog(this, "Fill Color", Color.yellow);
            colorButton.setForeground(fc);
        });
        controlPanel.add(colorButton);
        JButton lineColor = new JButton("Line Color");
        lineColor.addActionListener((ActionEvent a) -> {

            lc = JColorChooser.showDialog(this, "Line Color", Color.yellow);
            lineColor.setForeground(lc);
        });
        controlPanel.add(lineColor);
        filledCheckbox = new JCheckBox("Filled");
        controlPanel.add(filledCheckbox);
        add(controlPanel, BorderLayout.NORTH);
        JButton undo = new JButton("Undo");
        undo.addActionListener((ActionEvent ae) -> center.undo());
        controlPanel.add(undo);
        JButton clear = new JButton("Clear");
        controlPanel.add(clear);
        clear.addActionListener((ActionEvent ae) -> center.clear());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

//    Shape line1 = ShapesFactory.createShape(Shapes.LINE, 100, 100, 300, 300, Color.ORANGE, false, Color.cyan);
//    Shape square = ShapesFactory.createShape(Shapes.RECT, 200, 100, 300, 300, Color.green, true, Color.red);
//    center.add(square);
//    Triangle triangle = new Triangle(140, 30, 30, 50);
//    center.add(triangle);
//    Shape oval = ShapesFactory.createShape(Shapes.OVAL, 200, 100, 300, 300, Color.GRAY, true, Color.PINK);
//    center.add(oval);
//    center.add(line1);
    }

    public static void main(String[] args) {
        Editor editer = new Editor();
        editer.setSize(800, 800);
        editer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editer.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentShape = ShapesFactory.create(currentShapeType, e.getX() - XOFF, e.getY() - YOFF, e.getX() - XOFF, e.getY() - YOFF, lc, filledCheckbox.isSelected(), fc);
        center.add(currentShape);
        center.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentShape = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentShape != null) {
            currentShape.setP2(new Point(e.getX() - XOFF, e.getY() - YOFF));
            center.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        status.setText(String.format("(%03d, %03d)", e.getX() - XOFF, e.getY() - YOFF));
    }

}
