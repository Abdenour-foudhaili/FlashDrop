package produitservice.produitservice;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping({"/products", "/products/"})
@Validated
public class ProductController {

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping({"", "/"})
	public List<Product> list() {
		return productRepository.findAll();
	}

	@GetMapping({"/paged", "/paged/"})
	public Page<Product> listPaged(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "name") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction)
				? Sort.Direction.DESC
				: Sort.Direction.ASC;

		String normalizedSortBy = normalizeSortBy(sortBy);
		Pageable pageable = PageRequest.of(
				Math.max(page, 0),
				Math.min(Math.max(size, 1), 100),
				Sort.by(sortDirection, normalizedSortBy));

		return productRepository.findAll(pageable);
	}

	@GetMapping({"/{id}", "/{id}/"})
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		return productRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping({"", "/"})
	public ResponseEntity<Product> create(@Valid @RequestBody CreateProductRequest request) {
		validateDates(request.startDate(), request.endDate());
		Product created = productRepository.save(toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping({"/{id}", "/{id}/"})
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody CreateProductRequest request) {
		validateDates(request.startDate(), request.endDate());
		return productRepository.findById(id)
				.map(existing -> {
					existing.setName(request.name());
					existing.setDescription(request.description());
					existing.setPrice(request.price());
					existing.setStock(request.stock());
					existing.setCategory(request.category());
					existing.setDiscountPercentage(request.discountPercentage());
					existing.setStartDate(request.startDate());
					existing.setEndDate(request.endDate());
					existing.setImages(request.images());
					return ResponseEntity.ok(productRepository.save(existing));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PatchMapping({"/{id}/stock", "/{id}/stock/"})
	public ResponseEntity<Product> updateStock(@PathVariable Long id, @Valid @RequestBody UpdateStockRequest request) {
		return productRepository.findById(id)
				.map(existing -> {
					existing.setStock(request.stock());
					return ResponseEntity.ok(productRepository.save(existing));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping({"/{id}", "/{id}/"})
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		productRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private Product toEntity(CreateProductRequest request) {
		return new Product(
				request.name(),
				request.description(),
				request.price(),
				request.stock(),
				request.category(),
				request.discountPercentage(),
				request.startDate(),
				request.endDate(),
				request.images());
	}

	private String normalizeSortBy(String sortBy) {
		Set<String> allowedFields = Set.of("id", "name", "category", "price", "stock", "discountPercentage", "createdAt", "updatedAt");
		return allowedFields.contains(sortBy) ? sortBy : "name";
	}

	private void validateDates(OffsetDateTime startDate, OffsetDateTime endDate) {
		if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("Flash start date must be before or equal to flash end date.");
		}
	}

	public record CreateProductRequest(
			@NotBlank(message = "Name is required")
			@Size(max = 120, message = "Name must contain at most 120 characters")
			String name,
			@NotBlank(message = "Description is required")
			@Size(max = 1500, message = "Description must contain at most 1500 characters")
			String description,
			@NotNull(message = "Price is required")
			@DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
			BigDecimal price,
			@NotNull(message = "Stock is required")
			@Min(value = 1, message = "Stock must be greater than 0")
			Integer stock,
			@NotBlank(message = "Category is required")
			@Size(max = 120, message = "Category must contain at most 120 characters")
			String category,
			@NotNull(message = "Discount percentage is required")
			@Min(value = 0, message = "Discount percentage must be greater than or equal to 0")
			@Max(value = 100, message = "Discount percentage must be less than or equal to 100")
			Integer discountPercentage,
			@NotNull(message = "Flash start date is required")
			OffsetDateTime startDate,
			@NotNull(message = "Flash end date is required")
			OffsetDateTime endDate,
			@NotEmpty(message = "At least one product image is required")
			@Size(max = 8, message = "At most 8 images are allowed")
			List<@NotBlank(message = "Image content cannot be empty") String> images) {
	}

	public record UpdateStockRequest(
			@NotNull(message = "Stock is required")
			@Min(value = 1, message = "Stock must be greater than 0")
			Integer stock) {
	}
}
