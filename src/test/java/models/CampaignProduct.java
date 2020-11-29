package models;

import java.util.Objects;

public class CampaignProduct {

  private String name;
  private String price;
  private String discountPrice;




  public CampaignProduct withName(String name) {
    this.name = name;
    return this;
  }


  public CampaignProduct withPrice(String price) {
    this.price = price;
    return this;
  }



  public CampaignProduct withDiscountPrice(String discountPrice) {
    this.discountPrice = discountPrice;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }

  public String getDiscountPrice() {
    return discountPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CampaignProduct product = (CampaignProduct) o;
    return Objects.equals(name, product.name) &&
            Objects.equals(price, product.price) &&
            Objects.equals(discountPrice, product.discountPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price, discountPrice);
  }


}
