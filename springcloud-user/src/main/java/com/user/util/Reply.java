package com.user.util;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Reply {

	private State state;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Reply(int code, String message) {
		state = new State();
		state.stateCode = code;
		state.stateMessage = message;
	}

	private static class State {

		private int stateCode;

		private String stateMessage;

		@SuppressWarnings("unused")
		public int getStateCode() {
			return stateCode;
		}

		@SuppressWarnings("unused")
		public void setStateCode(int stateCode) {
			this.stateCode = stateCode;
		}

		@SuppressWarnings("unused")
		public String getStateMessage() {
			return stateMessage;
		}

		@SuppressWarnings("unused")
		public void setStateMessage(String stateMessage) {
			this.stateMessage = stateMessage;
		}

	}

	public Reply message(String message) {
		state.stateMessage = message;
		return this;
	}

	public Reply data(Object data) {
		this.data = data;
		return this;
	}

	public static Reply success() {
		return new Reply(0, "成功");
	}

	public static Reply fail() {
		return new Reply(1, "失败");
	}

}
