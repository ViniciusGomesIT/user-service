package br.com.api.rest.response;

public class GenericResponse {

	private Object data;
	private boolean updated;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean getUpdated() {
		return updated;
	}

	public void setUpdated(boolean isUpdated) {
		this.updated = isUpdated;
	}
}
