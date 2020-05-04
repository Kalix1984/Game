package application.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Keyboard {
	Scene scene;

	private boolean isLeft;
	private boolean isRight;
	private boolean isUp;
	private boolean isDown;
	private boolean isTap;
	private boolean isStart;

	private final KeyCode leftKey;
	private final KeyCode rightKey;
	private final KeyCode upKey;
	private final KeyCode downKey;
	private final KeyCode tapKey;
	private final KeyCode startKey;

	public Keyboard(Scene scene, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, KeyCode downKey, KeyCode tapKey, KeyCode startKey) {
		this.scene = scene;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.upKey = upKey;
		this.downKey = downKey;
		this.tapKey = tapKey;
		this.startKey = startKey;

		createKeyListeners();
	}

	public boolean isLeft() {
		return isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public boolean isUp() {
		return isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public boolean isTap() {
		return isTap;
	}

	public void resetUp() {
		this.isUp = false;
	}

	public void resetDown() {
		this.isDown = false;
	}

	public void resetTap() {
		this.isTap = false;
	}

	public boolean isStart() {
		return isStart;
	}

	private void createKeyListeners() {
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == leftKey) {
				isLeft = true;
			} else if (e.getCode() == rightKey) {
				isRight = true;
			} else if (e.getCode() == startKey) {
				isStart = true;
			}
		});

		scene.setOnKeyReleased(e -> {

			if (e.getCode() == leftKey) {
				isLeft = false;
			} else if (e.getCode() == rightKey) {
				isRight = false;
			} else if (e.getCode() == upKey) {
				isUp = true;
			} else if (e.getCode() == downKey) {
				isDown = true;
			} else if (e.getCode() == tapKey) {
				isTap = true;
			} else if (e.getCode() == startKey) {
				isStart = false;
			}
		});
	}

}
