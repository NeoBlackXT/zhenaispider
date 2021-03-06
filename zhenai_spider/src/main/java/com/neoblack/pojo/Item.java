package com.neoblack.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="tb_item_other")
public class Item extends BasePojo{
	@Id
	private Long id;
	private String cwhere;	//出处
	private String title;
	private String sellPoint;
	private Long price;
	private Integer num;
	private String barcode;
	private String image;	//最多5张图片
	private Long cid;
	private Integer status;
	
	@Transient
	private String desc;	//获取商品详情
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCwhere() {
		return cwhere;
	}
	public void setCwhere(String cwhere) {
		this.cwhere = cwhere;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", cwhere=" + cwhere + ", title=" + title + ", sellPoint=" + sellPoint + ", price="
				+ price + ", num=" + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status="
				+ status + ", desc=" + desc + "]";
	}
	
}
