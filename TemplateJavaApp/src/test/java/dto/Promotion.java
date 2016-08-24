package dto;

public class Promotion extends Payment {

	private String promotionCode;

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String toString() {
		return super.toString() + " " + "PROMO:>" + getPromotionCode();
	}

}
