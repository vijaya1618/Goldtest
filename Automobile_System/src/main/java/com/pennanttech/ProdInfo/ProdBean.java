package com.pennanttech.ProdInfo;

public class ProdBean {
	private long VehicleId;
	private String VehicleType;
	private String VehicleName;
	private String VehicleModel;
	private long VehicleYear;
	private long VehicleCost;
	private String VehicleColor;
	private long Quantity;
	private byte[] image;
	private String filename;


	
	public long getVehicleId() {
		return VehicleId;
	}
	public void setVehicleId(long vehicleId) {
		VehicleId = vehicleId;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public String getVehicleName() {
		return VehicleName;
	}
	public void setVehicleName(String vehicleName) {
		VehicleName = vehicleName;
	}
	public String getVehicleModel() {
		return VehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		VehicleModel = vehicleModel;
	}
	public long getVehicleYear() {
		return VehicleYear;
	}
	public void setVehicleYear(long vehicleYear) {
		VehicleYear = vehicleYear;
	}
	public long getVehicleCost() {
		return VehicleCost;
	}
	public void setVehicleCost(long vehicleCost) {
		VehicleCost = vehicleCost;
	}
	public String getVehicleColor() {
		return VehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		VehicleColor = vehicleColor;
	}
	public long getQuantity() {
		return Quantity;
	}
	public void setQuantity(long quantity) {
		Quantity = quantity;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
