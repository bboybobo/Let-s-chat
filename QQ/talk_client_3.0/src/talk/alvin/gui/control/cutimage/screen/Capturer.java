package talk.alvin.gui.control.cutimage.screen;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import talk.alvin.gui.frame.ChatFrame;

/**
 * 
 * @author zhangtao
 * @msn zht_dream@hotmail.com
 * @mail zht_dream@hotmail.com Let's Swing together.
 */
public class Capturer extends JComponent {
	private static final long serialVersionUID = 1L;

	private ScreenCapture screenCapture;

	private Color selectBorderColor = Color.GREEN;
	private Color selectFillColor = new Color(200, 200, 200, 60);
	private Color textColor = Color.BLUE;
	private Stroke selectStroke = new BasicStroke(1.0f);
	private Stroke resizeStroke = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
			BasicStroke.JOIN_MITER, 10.0f, new float[] { 10, 5 }, 0.2f);

	private JPanel controlPanel = getControlPanel();

	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Dimension screenSize = kit.getScreenSize();
	private Robot robot = null;

	private List<BorderStructure> borderList = new ArrayList<BorderStructure>();
	private int borderInflexionSize = 6;
	private Color borderInflexionColor = Color.RED;

	private BorderStructure editStructure = null;
	private Rectangle selectedRectangle = null;
	private Rectangle resizeRectangle;
	private Rectangle dragRectangle = null;

	private BufferedImage screenImage;
	private Point startPoint;
	private Point pressPoint;
	private Point dragPoint;
	private Point mousePoint;

	private boolean isEditing = false;
	private boolean isMoving = false;
	private int xMoveOffset;
	private int yMoveOffset;

	private Cursor predefinedCursor = Cursor
			.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	private Cursor dragMoveCursor = Cursor
			.getPredefinedCursor(Cursor.MOVE_CURSOR);

	public void setMousePoint(Point mousePoint) {
		this.mousePoint = mousePoint;
		this.repaint();
	}

	public Capturer(ScreenCapture screenCapture) {
		installMouseListener();
		this.screenCapture = screenCapture;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		screenCapture.getCaptureWindow().getLayeredPane().add(controlPanel,
				JLayeredPane.DEFAULT_LAYER.intValue() + 10);
	}

	private JButton createButton(String name, ActionListener l) {
		JButton button = new JButton(name);
		button.setOpaque(false);
		button.addActionListener(l);
		return button;
	}

	private JPanel getControlPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.setOpaque(false);
		panel.setBorder(null);
		panel.setBounds(100, 100, 250, 25);
		panel.setVisible(false);

		panel.add(createButton("OK", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle rectengle = selectedRectangle;
				BufferedImage subimage = screenImage.getSubimage(rectengle.x,
						rectengle.y, rectengle.width, rectengle.height);
				ChatFrame frame = screenCapture.getChatFrame();
				frame.getSendMessageTextArea().insertIcon(
						new ImageIcon(subimage));
				// screenCapture.getOverView().setSelectedImage(subimage);
				screenCapture.resume();
				exitCapturer();
			}
		}));
		panel.add(createButton("Cancel", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitCapturer();
			}
		}));
		panel.add(createButton("Close", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screenCapture.resume();
				exitCapturer();
			}
		}));
		return panel;
	}

	private void installMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressPoint = e.getPoint();
				if (isMoving) {
					xMoveOffset = pressPoint.x - selectedRectangle.x;
					yMoveOffset = pressPoint.y - selectedRectangle.y;
					return;
				}
				if (isEditing) {

				} else {
					setStartPoint(e.getPoint());
					setEndPoint(null);
					setDragPoint(null);
					controlPanel.setVisible(false);
				}
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					if (isMoving) {
						Rectangle rectengle = selectedRectangle;
						BufferedImage subimage = screenImage.getSubimage(
								rectengle.x, rectengle.y, rectengle.width,
								rectengle.height);
						screenCapture.getOverView().setSelectedImage(subimage);
						screenCapture.resume();
					} else {
						screenCapture.resume();
					}
					exitCapturer();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (getCursor() == dragMoveCursor && selectedRectangle != null) {
					return;
				}
				if (!isEditing) {
					setEndPoint(e.getPoint());
					if (getDragPoint() != null) {
						setDragPoint(null);
					}
				} else {
					if (resizeRectangle != null) {
						selectedRectangle = resizeRectangle;
						repaint();
					}
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				Point point = e.getPoint();
				if (isMoving) {
					selectedRectangle.x = point.x - xMoveOffset;
					selectedRectangle.y = point.y - yMoveOffset;
					if (selectedRectangle.x < 0) {
						selectedRectangle.x = 0;
					}
					if (selectedRectangle.y < 0) {
						selectedRectangle.y = 0;
					}
					if (selectedRectangle.x + selectedRectangle.width > screenSize.width) {
						selectedRectangle.x = screenSize.width
								- selectedRectangle.width;
					}
					if (selectedRectangle.y + selectedRectangle.height > screenSize.height) {
						selectedRectangle.y = screenSize.height
								- selectedRectangle.height;
					}
					repaint();
					return;
				}
				if (isEditing) {
					double x = selectedRectangle.x;
					double y = selectedRectangle.y;
					double w = selectedRectangle.width;
					double h = selectedRectangle.height;
					Point p1 = null;
					Point p2 = null;
					switch (editStructure.getPosition()) {
					case BorderStructure.POSITION_TOP:
						if (point.y >= (y + h)) {
							p1 = new Point((int) x, (int) (y + h));
							p2 = new Point((int) (x + w), point.y);
						} else {
							p1 = new Point((int) x, point.y);
							p2 = new Point((int) (x + w), (int) (y + h));
						}
						break;
					case BorderStructure.POSITION_BOTTOM:
						if (point.y < y) {
							p1 = new Point((int) x, point.y);
							p2 = new Point((int) (x + w), (int) y);
						} else {
							p1 = new Point((int) x, (int) y);
							p2 = new Point((int) (x + w), point.y);
						}
						break;
					case BorderStructure.POSITION_LEFT:
						if (point.x > (x + w)) {
							p1 = new Point((int) (x + w), (int) y);
							p2 = new Point(point.x, (int) (y + h));
						} else {
							p1 = new Point(point.x, (int) y);
							p2 = new Point((int) (x + w), (int) (y + h));
						}
						break;
					case BorderStructure.POSITION_RIGHT:
						if (point.x < x) {
							p1 = new Point(point.x, (int) y);
							p2 = new Point((int) x, (int) (y + h));
						} else {
							p1 = new Point((int) x, (int) y);
							p2 = new Point(point.x, (int) (y + h));
						}
						break;
					case BorderStructure.POSITION_TOPLEFT:

						p1 = new Point(point.x, point.y);
						p2 = new Point((int) (x + w), (int) (y + h));
						break;
					case BorderStructure.POSITION_TOPRIGHT:
						p1 = new Point((int) x, point.y);
						p2 = new Point(point.x, (int) (y + h));
						break;
					case BorderStructure.POSITION_BOTTOMLEFT:
						p1 = new Point(point.x, (int) y);
						p2 = new Point((int) (x + w), point.y);
						break;
					case BorderStructure.POSITION_BOTTOMRIGHT:
						p1 = new Point((int) x, (int) y);
						p2 = new Point(point.x, point.y);
						break;
					}
					resizeRectangle = getRectangle(p1, p2);
					repaint();
				} else {
					setDragPoint(point);
					setEndPoint(null);
					setMousePoint(point);
				}
			}

			public void mouseMoved(MouseEvent e) {
				Point point = e.getPoint();
				setMousePoint(point);
				int size = borderList.size();
				setCursor(predefinedCursor);
				isMoving = false;
				isEditing = false;
				editStructure = null;
				resizeRectangle = null;
				if (selectedRectangle != null) {
					if (selectedRectangle.contains(point)) {
						isMoving = true;
						setCursor(dragMoveCursor);
						return;
					}
				}
				for (int i = 0; i < size; i++) {
					BorderStructure structure = (BorderStructure) borderList
							.get(i);
					if (structure.getBorder().contains(point)) {
						setCursor(structure.getCursor());
						isEditing = true;
						editStructure = structure;
						break;
					}
				}
			}
		});

	}

	public void setScreenImage() {
		this.setCursor(predefinedCursor);
		screenImage = robot.createScreenCapture(new Rectangle(0, 0,
				screenSize.width, screenSize.height));
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(screenImage, 0, 0, this);
		g2d.setStroke(resizeStroke);
		Rectangle rectangle = null;
		if (dragRectangle != null) {
			g2d.setColor(selectBorderColor.darker());
			rectangle = dragRectangle;
			g2d.draw(rectangle);
			g2d.setColor(selectFillColor);
			g2d.fill(rectangle);
		}

		if (selectedRectangle != null) {
			g2d.setStroke(selectStroke);
			g2d.setColor(selectBorderColor.darker());
			rectangle = selectedRectangle;
			g2d.draw(rectangle);
			g2d.setColor(selectFillColor);
			g2d.fill(rectangle);

			setBorder(g2d, rectangle);

			controlPanel.setVisible(true);
			controlPanel.setLocation(getControlPanelLocation(rectangle));
		}
		if (rectangle != null) {
			g2d.setColor(textColor);
			g2d.drawString(rectangle.width + " x " + rectangle.height,
					rectangle.x, rectangle.y);
		}
		if (mousePoint != null && !controlPanel.isVisible()) {
			g2d.setColor(textColor);
			g2d.drawString("x=" + mousePoint.x + " y=" + mousePoint.y,
					mousePoint.x, mousePoint.y);
		}
		if (resizeRectangle != null) {
			g2d.setStroke(resizeStroke);
			g2d.setXORMode(Color.WHITE);
			g2d.setColor(selectBorderColor.darker());
			g2d.draw(resizeRectangle);
		}
	}

	private void setBorder(Graphics2D g, Rectangle rectangle) {
		borderList = new ArrayList<BorderStructure>();
		double x = rectangle.getX();
		double y = rectangle.getY();
		double w = rectangle.getWidth();
		double h = rectangle.getHeight();
		double centerX = rectangle.getCenterX();
		double centerY = rectangle.getCenterY();
		Rectangle2D.Double top = new Rectangle2D.Double(centerX
				- borderInflexionSize / 2, y - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(top, BorderStructure.POSITION_TOP));
		Rectangle2D.Double bottom = new Rectangle2D.Double(centerX
				- borderInflexionSize / 2, y + h - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(bottom,
				BorderStructure.POSITION_BOTTOM));
		Rectangle2D.Double left = new Rectangle2D.Double(x
				- borderInflexionSize / 2, centerY - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList
				.add(new BorderStructure(left, BorderStructure.POSITION_LEFT));
		Rectangle2D.Double right = new Rectangle2D.Double(x + w
				- borderInflexionSize / 2, centerY - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(right,
				BorderStructure.POSITION_RIGHT));
		Rectangle2D.Double topLeft = new Rectangle2D.Double(x
				- borderInflexionSize / 2, y - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(topLeft,
				BorderStructure.POSITION_TOPLEFT));
		Rectangle2D.Double topRight = new Rectangle2D.Double(x + w
				- borderInflexionSize / 2, y - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(topRight,
				BorderStructure.POSITION_TOPRIGHT));
		Rectangle2D.Double bottomLeft = new Rectangle2D.Double(x
				- borderInflexionSize / 2, y + h - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(bottomLeft,
				BorderStructure.POSITION_BOTTOMLEFT));
		Rectangle2D.Double bottomRight = new Rectangle2D.Double(x + w
				- borderInflexionSize / 2, y + h - borderInflexionSize / 2,
				borderInflexionSize, borderInflexionSize);
		borderList.add(new BorderStructure(bottomRight,
				BorderStructure.POSITION_BOTTOMRIGHT));
		int size = borderList.size();
		g.setColor(borderInflexionColor);
		for (int i = 0; i < size; i++) {
			BorderStructure structure = (BorderStructure) borderList.get(i);
			g.fill(structure.getBorder());
		}

	}

	private Point getControlPanelLocation(Rectangle rectengle) {
		Point point = new Point(rectengle.x + rectengle.width
				- controlPanel.getWidth(), rectengle.y + rectengle.height);
		if ((point.x) < 0) {
			point.x = 0;
		}
		if (((point.y + controlPanel.getHeight()) > screenSize.height)) {
			point.y = screenSize.height - controlPanel.getHeight();
		}
		return point;
	}

	private Rectangle getRectangle(Point p1, Point p2) {
		int width = Math.abs(p2.x - p1.x);
		int height = Math.abs(p2.y - p1.y);
		if ((p1.x <= p2.x)) {
			if (p1.y < p2.y) {
				return new Rectangle(p1.x, p1.y, width, height);
			} else {
				return new Rectangle(p1.x, p2.y, width, height);
			}
		} else {
			if (p1.y < p2.y) {
				return new Rectangle(p2.x, p1.y, width, height);
			} else {
				return new Rectangle(p2.x, p2.y, width, height);
			}
		}
	}

	private void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		this.repaint();
	}

	private void setEndPoint(Point endPoint) {
		if (endPoint != null) {
			selectedRectangle = getRectangle(startPoint, endPoint);
			if (selectedRectangle.getWidth() == 0
					|| selectedRectangle.getHeight() == 0) {
				selectedRectangle = null;
			}
		} else {
			selectedRectangle = null;
		}
	}

	private Point getDragPoint() {
		return dragPoint;
	}

	private void setDragPoint(Point dragPoint) {
		this.dragPoint = dragPoint;
		if (dragPoint == null) {
			dragRectangle = null;
		} else {
			dragRectangle = getRectangle(startPoint, dragPoint);
			if (dragRectangle.getWidth() == 0 || dragRectangle.getHeight() == 0) {
				dragRectangle = null;
			}
		}
		this.repaint();
	}

	private void exitCapturer() {
		setStartPoint(null);
		setEndPoint(null);
		setDragPoint(null);
		controlPanel.setVisible(false);
	}

	public Color getFillColor() {
		return selectFillColor;
	}

	public void setFillColor(Color fillColor) {
		this.selectFillColor = fillColor;
		this.repaint();
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
		this.repaint();
	}

	public Stroke getBorderStroke() {
		return resizeStroke;
	}

	public void setBorderStroke(Stroke borderStroke) {
		this.resizeStroke = borderStroke;
		this.repaint();
	}

	public Stroke getResizeStroke() {
		return resizeStroke;
	}

	public void setResizeStroke(Stroke resizeStroke) {
		this.resizeStroke = resizeStroke;
		this.repaint();
	}

	public Cursor getPredefinedCursor() {
		return predefinedCursor;
	}

	public void setPredefinedCursor(Cursor predefinedCursor) {
		this.predefinedCursor = predefinedCursor;
		this.repaint();
	}

	public Color getSelectBorderColor() {
		return selectBorderColor;
	}

	public void setSelectBorderColor(Color selectBorderColor) {
		this.selectBorderColor = selectBorderColor;
		this.repaint();
	}

	public Color getSelectFillColor() {
		return selectFillColor;
	}

	public void setSelectFillColor(Color selectFillColor) {
		this.selectFillColor = selectFillColor;
		this.repaint();
	}

	public Stroke getSelectStroke() {
		return selectStroke;
	}

	public void setSelectStroke(Stroke selectStroke) {
		this.selectStroke = selectStroke;
		this.repaint();
	}

	public int getBorderInflexionSize() {
		return borderInflexionSize;
	}

	public void setBorderInflexionSize(int borderInflexionSize) {
		this.borderInflexionSize = borderInflexionSize;
		this.repaint();
	}

	public Color getBorderInflexionColor() {
		return borderInflexionColor;
	}

	public void setBorderInflexionColor(Color borderInflexionColor) {
		this.borderInflexionColor = borderInflexionColor;
		this.repaint();
	}

}