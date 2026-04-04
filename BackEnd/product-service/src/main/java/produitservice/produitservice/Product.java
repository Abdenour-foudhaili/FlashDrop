package produitservice.produitservice;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "LONGTEXT")
	private String description;

	@Column(nullable = false, precision = 19, scale = 2)
	private BigDecimal price;

	@Column
	private Integer stock;

	@Column(length = 120)
	private String category;

	@Column(name = "discount_percentage")
	private Integer discountPercentage;

	@Column(name = "start_date")
	private OffsetDateTime startDate;

	@Column(name = "end_date")
	private OffsetDateTime endDate;

	@Column(name = "created_at", updatable = false)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;

	@Column(name = "image_url", columnDefinition = "LONGTEXT")
	private String imageUrl;

	@Convert(converter = StringListJsonConverter.class)
	@Column(name = "images", columnDefinition = "LONGTEXT")
	private List<String> images = new ArrayList<>();

	protected Product() {
		// for JPA
	}

	public Product(String name, String description, BigDecimal price, Integer stock, String category,
			Integer discountPercentage, OffsetDateTime startDate, OffsetDateTime endDate, List<String> images) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
		this.discountPercentage = discountPercentage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.images = images == null ? new ArrayList<>() : images;
		this.imageUrl = resolveImageUrl(this.images);
	}

	@PrePersist
	void onCreate() {
		OffsetDateTime now = OffsetDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;
		if (this.stock == null) {
			this.stock = 0;
		}
		if (this.discountPercentage == null) {
			this.discountPercentage = 0;
		}
		if (this.images == null) {
			this.images = new ArrayList<>();
		}
		this.imageUrl = resolveImageUrl(this.images);
	}

	@PreUpdate
	void onUpdate() {
		this.updatedAt = OffsetDateTime.now();
		if (this.stock == null) {
			this.stock = 0;
		}
		if (this.discountPercentage == null) {
			this.discountPercentage = 0;
		}
		if (this.images == null) {
			this.images = new ArrayList<>();
		}
		this.imageUrl = resolveImageUrl(this.images);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock == null ? 0 : stock;
	}

	public String getCategory() {
		return category == null || category.isBlank() ? "Other" : category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public List<String> getImages() {
		if ((images == null || images.isEmpty()) && imageUrl != null && !imageUrl.isBlank()) {
			return List.of(imageUrl);
		}
		return images;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImages(List<String> images) {
		this.images = images == null ? new ArrayList<>() : images;
		this.imageUrl = resolveImageUrl(this.images);
	}

	private String resolveImageUrl(List<String> imageCandidates) {
		if (imageCandidates == null || imageCandidates.isEmpty()) {
			return null;
		}

		String firstImage = imageCandidates.get(0);
		if (firstImage == null) {
			return null;
		}

		String trimmed = firstImage.trim();
		if (trimmed.isEmpty()) {
			return null;
		}

		boolean isHttpUrl = trimmed.startsWith("http://") || trimmed.startsWith("https://");
		if (!isHttpUrl || trimmed.length() > 255) {
			return null;
		}

		return trimmed;
	}

	public String getAvailability() {
		return stock != null && stock > 0 ? "In stock" : "Out of stock";
	}
}
