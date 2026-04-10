(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["features-produits-produits-module"],{

/***/ "2EGI":
/*!********************************************************************************!*\
  !*** ./src/app/features/produits/pages/product-form/product-form.component.ts ***!
  \********************************************************************************/
/*! exports provided: ProductFormComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductFormComponent", function() { return ProductFormComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../data-access/catalog-product.service */ "OvNt");





let ProductFormComponent = class ProductFormComponent {
    constructor(fb, route, productsService, router) {
        this.fb = fb;
        this.route = route;
        this.productsService = productsService;
        this.router = router;
        this.categories = ['Electronique', 'Mode', 'Maison', 'Beaute', 'Sport', 'Autre'];
        this.maxImages = 8;
        this.maxImageSizeMb = 5;
        this.imagePreviews = [];
        this.isEditMode = false;
        this.imageError = '';
        this.submitting = false;
        this.error = '';
        this.form = this.fb.group({
            name: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].minLength(2), _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].maxLength(120)]],
            description: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].minLength(5), _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].maxLength(1500)]],
            price: [0, [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].min(0.01)]],
            stock: [1, [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].min(1)]],
            category: ['Other', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
            discountPercentage: [0, [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required, _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].min(0), _angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].max(100)]],
            startDate: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
            endDate: ['', [_angular_forms__WEBPACK_IMPORTED_MODULE_2__["Validators"].required]],
        }, { validators: this.dateRangeValidator() });
        const idParam = this.route.snapshot.paramMap.get('id');
        if (idParam) {
            this.isEditMode = true;
            this.productsService.getProductById(Number(idParam)).subscribe({
                next: (product) => {
                    this.editingProduct = product;
                    this.imagePreviews = [...(product.images || [])];
                    this.form.patchValue({
                        name: product.name,
                        description: product.description,
                        price: product.price,
                        stock: product.stock,
                        category: product.category,
                        discountPercentage: product.discountPercentage,
                        startDate: this.toInputDate(product.startDate),
                        endDate: this.toInputDate(product.endDate),
                    });
                },
                error: () => this.router.navigate(['/produits']),
            });
        }
    }
    onImagesSelected(event) {
        return Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"])(this, void 0, void 0, function* () {
            this.imageError = '';
            const input = event.target;
            if (!input.files || !input.files.length) {
                return;
            }
            const files = Array.from(input.files);
            if (this.imagePreviews.length + files.length > this.maxImages) {
                this.imageError = 'Maximum ' + this.maxImages + ' images autorisees.';
                return;
            }
            const invalidType = files.find((file) => !file.type.startsWith('image/'));
            if (invalidType) {
                this.imageError = 'Seuls les fichiers image sont autorises.';
                return;
            }
            const invalidSize = files.find((file) => file.size > this.maxImageSizeMb * 1024 * 1024);
            if (invalidSize) {
                this.imageError = 'Chaque image doit etre <= ' + this.maxImageSizeMb + 'MB.';
                return;
            }
            const base64Images = yield Promise.all(files.map((file) => this.readFileAsDataUrl(file)));
            this.imagePreviews = [...this.imagePreviews, ...base64Images];
            input.value = '';
        });
    }
    removeImage(index) {
        this.imagePreviews = this.imagePreviews.filter((_value, i) => i !== index);
        this.validateImages();
    }
    submit() {
        if (this.form.invalid || this.submitting) {
            this.form.markAllAsTouched();
            return;
        }
        if (!this.validateImages()) {
            return;
        }
        const value = this.form.getRawValue();
        this.submitting = true;
        this.error = '';
        const payload = {
            name: String(value.name || '').trim(),
            description: String(value.description || '').trim(),
            price: Number(value.price || 0),
            stock: Number(value.stock || 1),
            category: String(value.category || 'Other').trim() || 'Other',
            discountPercentage: Number(value.discountPercentage || 0),
            startDate: this.toIsoDate(String(value.startDate || '')),
            endDate: this.toIsoDate(String(value.endDate || '')),
            images: this.imagePreviews,
        };
        const request$ = this.isEditMode && this.editingProduct
            ? this.productsService.updateProduct(this.editingProduct.id, payload)
            : this.productsService.createProduct(payload);
        request$.subscribe({
            next: () => {
                this.submitting = false;
                this.router.navigate(['/produits']);
            },
            error: (apiError) => {
                this.submitting = false;
                this.error = this.extractApiError(apiError);
            },
        });
    }
    cancel() {
        this.router.navigate(['/produits']);
    }
    readFileAsDataUrl(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.onload = () => resolve(String(reader.result));
            reader.onerror = () => reject(new Error('Echec de lecture du fichier.'));
            reader.readAsDataURL(file);
        });
    }
    toInputDate(isoDate) {
        return isoDate ? isoDate.slice(0, 10) : '';
    }
    toIsoDate(value) {
        if (!value) {
            return new Date().toISOString();
        }
        const parsed = new Date(value);
        if (Number.isNaN(parsed.getTime())) {
            return new Date().toISOString();
        }
        return parsed.toISOString();
    }
    validateImages() {
        if (!this.imagePreviews.length) {
            this.imageError = 'Au moins une image est obligatoire.';
            return false;
        }
        if (this.imagePreviews.length > this.maxImages) {
            this.imageError = 'Maximum ' + this.maxImages + ' images autorisees.';
            return false;
        }
        this.imageError = '';
        return true;
    }
    dateRangeValidator() {
        return (control) => {
            var _a, _b;
            const startDate = (_a = control.get('startDate')) === null || _a === void 0 ? void 0 : _a.value;
            const endDate = (_b = control.get('endDate')) === null || _b === void 0 ? void 0 : _b.value;
            if (!startDate || !endDate) {
                return null;
            }
            return new Date(startDate) <= new Date(endDate)
                ? null
                : { dateRange: true };
        };
    }
    extractApiError(apiError) {
        var _a, _b;
        const fieldErrors = (_a = apiError === null || apiError === void 0 ? void 0 : apiError.error) === null || _a === void 0 ? void 0 : _a.fieldErrors;
        if (fieldErrors && typeof fieldErrors === 'object') {
            const messages = Object.values(fieldErrors).map((value) => String(value));
            if (messages.length) {
                return messages.join(' | ');
            }
        }
        if ((_b = apiError === null || apiError === void 0 ? void 0 : apiError.error) === null || _b === void 0 ? void 0 : _b.message) {
            return String(apiError.error.message);
        }
        return 'Impossible d\'enregistrer le produit. Verifiez tous les champs.';
    }
};
ProductFormComponent.ctorParameters = () => [
    { type: _angular_forms__WEBPACK_IMPORTED_MODULE_2__["FormBuilder"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["ActivatedRoute"] },
    { type: _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_4__["CatalogProductService"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] }
];
ProductFormComponent = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-form',
        template: `
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">{{ isEditMode ? 'Modifier le produit' : 'Creer un produit' }}</h4>

        <form [formGroup]="form" (ngSubmit)="submit()" class="mt-3">
          <div class="form-group">
            <label>Nom</label>
            <input type="text" class="form-control" formControlName="name" />
            <small class="text-danger" *ngIf="form.get('name')?.touched && form.get('name')?.errors?.required">Le nom est obligatoire.</small>
            <small class="text-danger" *ngIf="form.get('name')?.touched && form.get('name')?.errors?.minlength">Le nom doit contenir au moins 2 caracteres.</small>
            <small class="text-danger" *ngIf="form.get('name')?.touched && form.get('name')?.errors?.maxlength">Le nom doit contenir au maximum 120 caracteres.</small>
          </div>

          <div class="form-group">
            <label>Categorie</label>
            <select class="form-control" formControlName="category">
              <option *ngFor="let category of categories" [value]="category">{{ category }}</option>
            </select>
            <small class="text-danger" *ngIf="form.get('category')?.touched && form.get('category')?.errors?.required">La categorie est obligatoire.</small>
          </div>

          <div class="form-group">
            <label>Description</label>
            <textarea rows="3" class="form-control" formControlName="description"></textarea>
            <small class="text-danger" *ngIf="form.get('description')?.touched && form.get('description')?.errors?.required">La description est obligatoire.</small>
            <small class="text-danger" *ngIf="form.get('description')?.touched && form.get('description')?.errors?.minlength">La description doit contenir au moins 5 caracteres.</small>
            <small class="text-danger" *ngIf="form.get('description')?.touched && form.get('description')?.errors?.maxlength">La description doit contenir au maximum 1500 caracteres.</small>
          </div>

          <div class="form-row">
            <div class="form-group col-md-4">
              <label>Prix</label>
              <input type="number" min="0" step="0.01" class="form-control" formControlName="price" />
              <small class="text-danger" *ngIf="form.get('price')?.touched && form.get('price')?.errors?.required">Le prix est obligatoire.</small>
              <small class="text-danger" *ngIf="form.get('price')?.touched && form.get('price')?.errors?.min">Le prix doit etre superieur a 0.</small>
            </div>
            <div class="form-group col-md-4">
              <label>Stock</label>
              <input type="number" min="1" class="form-control" formControlName="stock" />
              <small class="text-danger" *ngIf="form.get('stock')?.touched && form.get('stock')?.errors?.required">Le stock est obligatoire.</small>
              <small class="text-danger" *ngIf="form.get('stock')?.touched && form.get('stock')?.errors?.min">Le stock doit etre superieur a 0.</small>
            </div>
            <div class="form-group col-md-4">
              <label>Remise (%)</label>
              <input type="number" min="0" max="100" class="form-control" formControlName="discountPercentage" />
              <small class="text-danger" *ngIf="form.get('discountPercentage')?.touched && form.get('discountPercentage')?.errors?.required">La remise est obligatoire.</small>
              <small class="text-danger" *ngIf="form.get('discountPercentage')?.touched && (form.get('discountPercentage')?.errors?.min || form.get('discountPercentage')?.errors?.max)">La remise doit etre entre 0 et 100.</small>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group col-md-6">
              <label>Date debut flash</label>
              <input type="date" class="form-control" formControlName="startDate" />
              <small class="text-danger" *ngIf="form.get('startDate')?.touched && form.get('startDate')?.errors?.required">La date de debut est obligatoire.</small>
            </div>
            <div class="form-group col-md-6">
              <label>Date fin flash</label>
              <input type="date" class="form-control" formControlName="endDate" />
              <small class="text-danger" *ngIf="form.get('endDate')?.touched && form.get('endDate')?.errors?.required">La date de fin est obligatoire.</small>
            </div>
          </div>

          <small class="text-danger d-block mb-3" *ngIf="form.touched && form.errors?.dateRange">
            La date de debut flash doit etre inferieure ou egale a la date de fin.
          </small>

          <div class="form-group">
            <label>Importer des images produit</label>
            <input type="file" class="form-control" multiple accept="image/*" (change)="onImagesSelected($event)" />
            <small class="text-danger" *ngIf="imageError">{{ imageError }}</small>
          </div>

          <div class="row" *ngIf="imagePreviews.length">
            <div class="col-md-3 mb-3" *ngFor="let image of imagePreviews; let i = index">
              <div class="border rounded p-2">
                <img [src]="image" alt="Product image preview" class="img-fluid rounded mb-2" />
                <button type="button" class="btn btn-sm btn-outline-danger btn-block" (click)="removeImage(i)">Retirer</button>
              </div>
            </div>
          </div>

          <div *ngIf="error" class="alert alert-warning">{{ error }}</div>

          <div class="d-flex">
            <button type="submit" class="btn btn-primary mr-2" [disabled]="submitting || form.invalid">
              {{ submitting ? 'Enregistrement...' : (isEditMode ? 'Mettre a jour' : 'Creer') }}
            </button>
            <button type="button" class="btn btn-light" (click)="cancel()">Annuler</button>
          </div>
        </form>
      </div>
    </div>
  `,
    })
], ProductFormComponent);



/***/ }),

/***/ "OvNt":
/*!**************************************************************************!*\
  !*** ./src/app/features/produits/data-access/catalog-product.service.ts ***!
  \**************************************************************************/
/*! exports provided: CatalogProductService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CatalogProductService", function() { return CatalogProductService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../../../environments/environment */ "AytR");






let CatalogProductService = class CatalogProductService {
    constructor(http) {
        this.http = http;
        this.apiUrl = `${_environments_environment__WEBPACK_IMPORTED_MODULE_5__["environment"].apiGatewayBaseUrl}/products`;
        this.productsSubject = new rxjs__WEBPACK_IMPORTED_MODULE_3__["BehaviorSubject"]([]);
        this.products$ = this.productsSubject.asObservable();
        this.refreshProducts();
    }
    refreshProducts() {
        this.http.get(this.apiUrl).subscribe({
            next: (products) => this.productsSubject.next(this.normalizeProducts(products)),
            error: () => this.productsSubject.next([]),
        });
    }
    getProductsSnapshot() {
        return this.productsSubject.value;
    }
    getProductsPage(query) {
        const params = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpParams"]()
            .set('page', String(query.page))
            .set('size', String(query.size))
            .set('sortBy', query.sortBy)
            .set('direction', query.direction);
        return this.http.get(`${this.apiUrl}/paged`, { params }).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["map"])((response) => (Object.assign(Object.assign({}, response), { content: this.normalizeProducts(response.content || []) }))));
    }
    getProductById(id) {
        return this.http
            .get(`${this.apiUrl}/${id}`)
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["map"])((product) => this.normalizeProduct(product)));
    }
    createProduct(payload) {
        return this.http.post(this.apiUrl, payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["map"])((product) => this.normalizeProduct(product)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["tap"])(() => this.refreshProducts()));
    }
    updateProduct(id, payload) {
        return this.http.put(`${this.apiUrl}/${id}`, payload).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["map"])((product) => this.normalizeProduct(product)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["tap"])(() => this.refreshProducts()));
    }
    deleteProduct(id) {
        return this.http.delete(`${this.apiUrl}/${id}`).pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["tap"])(() => this.refreshProducts()));
    }
    updateStock(id, stock) {
        return this.http
            .patch(`${this.apiUrl}/${id}/stock`, { stock })
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["map"])((product) => this.normalizeProduct(product)), Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_4__["tap"])(() => this.refreshProducts()));
    }
    normalizeProducts(products) {
        return (products || []).map((product) => this.normalizeProduct(product));
    }
    normalizeProduct(product) {
        const images = Array.isArray(product.images)
            ? product.images.filter((image) => typeof image === 'string' && image.trim().length > 0)
            : [];
        var normalizedImages = [];
        if (images.length > 0) {
            normalizedImages = images;
        }
        else if (product.imageUrl && product.imageUrl.trim().length > 0) {
            normalizedImages = [product.imageUrl];
        }
        const category = product.category && product.category.trim().length > 0 ? product.category : 'Other';
        const stock = Number(product.stock || 0);
        const price = Number(product.price || 0);
        const discountPercentage = Number(product.discountPercentage || 0);
        const finalPriceFromApi = Number(product.finalPrice);
        const finalPrice = Number.isFinite(finalPriceFromApi)
            ? finalPriceFromApi
            : this.computeFinalPrice(price, discountPercentage);
        const status = this.normalizeFlashStatus(product.status, stock);
        const availability = stock > 0 ? 'In stock' : 'Out of stock';
        return Object.assign(Object.assign({}, product), { price,
            finalPrice,
            status,
            category, images: normalizedImages, stock,
            discountPercentage,
            availability });
    }
    computeFinalPrice(price, discountPercentage) {
        const reduction = price * (discountPercentage / 100);
        return Number((price - reduction).toFixed(2));
    }
    normalizeFlashStatus(rawStatus, stock) {
        if (rawStatus === 'NOT_STARTED' || rawStatus === 'FLASH_ACTIVE' || rawStatus === 'EXPIRED' || rawStatus === 'OUT_OF_STOCK') {
            return rawStatus;
        }
        return stock > 0 ? 'FLASH_ACTIVE' : 'OUT_OF_STOCK';
    }
};
CatalogProductService.ctorParameters = () => [
    { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"] }
];
CatalogProductService = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Injectable"])({
        providedIn: 'root',
    })
], CatalogProductService);



/***/ }),

/***/ "P+VO":
/*!**************************************************************!*\
  !*** ./src/app/features/produits/produits-routing.module.ts ***!
  \**************************************************************/
/*! exports provided: ProduitsRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProduitsRoutingModule", function() { return ProduitsRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _pages_product_details_product_details_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./pages/product-details/product-details.component */ "z+1w");
/* harmony import */ var _pages_product_form_product_form_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./pages/product-form/product-form.component */ "2EGI");
/* harmony import */ var _pages_product_list_product_list_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./pages/product-list/product-list.component */ "TNFb");






const routes = [
    { path: '', component: _pages_product_list_product_list_component__WEBPACK_IMPORTED_MODULE_5__["ProductListComponent"] },
    { path: 'new', component: _pages_product_form_product_form_component__WEBPACK_IMPORTED_MODULE_4__["ProductFormComponent"] },
    { path: ':id/edit', component: _pages_product_form_product_form_component__WEBPACK_IMPORTED_MODULE_4__["ProductFormComponent"] },
    { path: ':id', component: _pages_product_details_product_details_component__WEBPACK_IMPORTED_MODULE_3__["ProductDetailsComponent"] },
];
let ProduitsRoutingModule = class ProduitsRoutingModule {
};
ProduitsRoutingModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]],
    })
], ProduitsRoutingModule);



/***/ }),

/***/ "TNFb":
/*!********************************************************************************!*\
  !*** ./src/app/features/produits/pages/product-list/product-list.component.ts ***!
  \********************************************************************************/
/*! exports provided: ProductListComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductListComponent", function() { return ProductListComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../data-access/catalog-product.service */ "OvNt");




let ProductListComponent = class ProductListComponent {
    constructor(productsService, route, router) {
        this.productsService = productsService;
        this.route = route;
        this.router = router;
        this.products = [];
        this.filteredProducts = [];
        this.pagedProducts = [];
        this.categories = [];
        this.pageSizeOptions = [5, 10, 20];
        this.currentPage = 1;
        this.pageSize = 5;
        this.totalPages = 1;
        this.sortField = 'name';
        this.sortDirection = 'asc';
        this.filter = {
            search: '',
            category: 'All',
            availability: 'All',
            minStock: null,
        };
        this.loading = true;
        this.error = '';
        this.initializedFromUrl = false;
    }
    ngOnInit() {
        this.restoreStateFromQueryParams();
        this.subscription = this.productsService.products$.subscribe((products) => {
            this.products = products;
            this.categories = ['All', ...new Set(products.map((item) => item.category))];
            if (!this.categories.includes(this.filter.category)) {
                this.filter.category = 'All';
            }
            this.applyFilters(!this.initializedFromUrl);
            this.initializedFromUrl = true;
        });
        this.reload();
    }
    ngOnDestroy() {
        var _a;
        (_a = this.subscription) === null || _a === void 0 ? void 0 : _a.unsubscribe();
    }
    reload() {
        this.loading = true;
        this.error = '';
        try {
            this.productsService.refreshProducts();
        }
        catch (_error) {
            this.loading = false;
            this.error = 'Echec du chargement des produits.';
        }
    }
    createProduct() {
        this.router.navigate(['/produits/new']);
    }
    applyFilters(resetPage = true) {
        const search = this.filter.search.trim().toLowerCase();
        const filteredProducts = this.products.filter((product) => {
            const matchesSearch = !search ||
                [product.name || '', product.description || '', product.category || ''].some((field) => String(field).toLowerCase().includes(search));
            const matchesCategory = this.filter.category === 'All' || product.category === this.filter.category;
            const matchesAvailability = this.filter.availability === 'All' || product.availability === this.filter.availability;
            const matchesMinStock = this.filter.minStock === null || product.stock >= this.filter.minStock;
            return matchesSearch && matchesCategory && matchesAvailability && matchesMinStock;
        });
        this.filteredProducts = this.sortProducts(filteredProducts);
        if (resetPage) {
            this.currentPage = 1;
        }
        this.updatePagedProducts();
        this.loading = false;
        this.error = '';
    }
    setSort(field) {
        if (this.sortField === field) {
            this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
        }
        else {
            this.sortField = field;
            this.sortDirection = 'asc';
        }
        this.filteredProducts = this.sortProducts(this.filteredProducts);
        this.updatePagedProducts();
    }
    previousPage() {
        if (this.currentPage <= 1) {
            return;
        }
        this.currentPage -= 1;
        this.updatePagedProducts();
    }
    nextPage() {
        if (this.currentPage >= this.totalPages) {
            return;
        }
        this.currentPage += 1;
        this.updatePagedProducts();
    }
    goToPage(page) {
        if (page < 1 || page > this.totalPages) {
            return;
        }
        this.currentPage = page;
        this.updatePagedProducts();
    }
    onPageSizeChange() {
        this.currentPage = 1;
        this.updatePagedProducts();
    }
    get pageNumbers() {
        return Array.from({ length: this.totalPages }, (_value, index) => index + 1);
    }
    getSortIndicator(field) {
        if (this.sortField !== field) {
            return '^v';
        }
        return this.sortDirection === 'asc' ? '^' : 'v';
    }
    viewDetails(productId) {
        this.router.navigate(['/produits', productId]);
    }
    editProduct(productId) {
        this.router.navigate(['/produits', productId, 'edit']);
    }
    deleteProduct(productId) {
        const shouldDelete = window.confirm('Supprimer ce produit ? Cette action est irreversible.');
        if (shouldDelete) {
            this.productsService.deleteProduct(productId).subscribe();
        }
    }
    decreaseStock(product) {
        if (product.stock <= 0) {
            return;
        }
        this.productsService.updateStock(product.id, product.stock - 1).subscribe();
    }
    increaseStock(product) {
        this.productsService.updateStock(product.id, product.stock + 1).subscribe();
    }
    resetFilters() {
        this.filter = {
            search: '',
            category: 'All',
            availability: 'All',
            minStock: null,
        };
        this.sortField = 'name';
        this.sortDirection = 'asc';
        this.currentPage = 1;
        this.pageSize = this.pageSizeOptions[0];
        this.applyFilters(false);
    }
    availabilityLabel(availability) {
        return availability === 'In stock' ? 'En stock' : 'Rupture';
    }
    flashStatusLabel(status) {
        switch (status) {
            case 'NOT_STARTED':
                return 'Pas commence';
            case 'FLASH_ACTIVE':
                return 'Flash actif';
            case 'EXPIRED':
                return 'Expire';
            case 'OUT_OF_STOCK':
                return 'Rupture flash';
            default:
                return 'Indetermine';
        }
    }
    flashStatusClass(status) {
        switch (status) {
            case 'NOT_STARTED':
                return 'badge-secondary';
            case 'FLASH_ACTIVE':
                return 'badge-info';
            case 'EXPIRED':
                return 'badge-dark';
            case 'OUT_OF_STOCK':
                return 'badge-warning text-dark';
            default:
                return 'badge-light';
        }
    }
    finalPriceValue(product) {
        if (product.finalPrice !== null && product.finalPrice !== undefined) {
            return Number(product.finalPrice);
        }
        return Number(product.price || 0);
    }
    sortProducts(products) {
        const direction = this.sortDirection === 'asc' ? 1 : -1;
        return [...products].sort((left, right) => {
            const leftValue = this.getSortValue(left, this.sortField);
            const rightValue = this.getSortValue(right, this.sortField);
            if (leftValue < rightValue) {
                return -1 * direction;
            }
            if (leftValue > rightValue) {
                return 1 * direction;
            }
            return 0;
        });
    }
    getSortValue(product, field) {
        var _a, _b;
        switch (field) {
            case 'price':
                return Number(product.price || 0);
            case 'finalPrice':
                return Number((_b = (_a = product.finalPrice) !== null && _a !== void 0 ? _a : product.price) !== null && _b !== void 0 ? _b : 0);
            case 'discountPercentage':
                return Number(product.discountPercentage || 0);
            case 'stock':
                return Number(product.stock || 0);
            case 'status':
                return String(product.status || '').toLowerCase();
            case 'category':
                return String(product.category || '').toLowerCase();
            case 'name':
            default:
                return String(product.name || '').toLowerCase();
        }
    }
    updatePagedProducts() {
        this.totalPages = Math.max(1, Math.ceil(this.filteredProducts.length / this.pageSize));
        if (this.currentPage > this.totalPages) {
            this.currentPage = this.totalPages;
        }
        const start = (this.currentPage - 1) * this.pageSize;
        const end = start + this.pageSize;
        this.pagedProducts = this.filteredProducts.slice(start, end);
        if (this.initializedFromUrl) {
            this.syncQueryParams();
        }
    }
    restoreStateFromQueryParams() {
        const queryParams = this.route.snapshot.queryParamMap;
        this.filter.search = queryParams.get('search') || '';
        this.filter.category = queryParams.get('category') || 'All';
        const availability = queryParams.get('availability');
        this.filter.availability =
            availability === 'In stock' || availability === 'Out of stock' || availability === 'All'
                ? availability
                : 'All';
        const minStockRaw = queryParams.get('minStock');
        this.filter.minStock =
            minStockRaw !== null && minStockRaw !== '' && !Number.isNaN(Number(minStockRaw))
                ? Number(minStockRaw)
                : null;
        const sort = queryParams.get('sort');
        if (sort === 'name' || sort === 'category' || sort === 'price' || sort === 'finalPrice' || sort === 'discountPercentage' || sort === 'stock' || sort === 'status') {
            this.sortField = sort;
        }
        const direction = queryParams.get('direction');
        if (direction === 'asc' || direction === 'desc') {
            this.sortDirection = direction;
        }
        const pageRaw = Number(queryParams.get('page'));
        this.currentPage = Number.isInteger(pageRaw) && pageRaw > 0 ? pageRaw : 1;
        const pageSizeRaw = Number(queryParams.get('size'));
        this.pageSize = this.pageSizeOptions.includes(pageSizeRaw) ? pageSizeRaw : this.pageSizeOptions[0];
    }
    syncQueryParams() {
        this.router.navigate([], {
            relativeTo: this.route,
            replaceUrl: true,
            queryParams: {
                search: this.filter.search || null,
                category: this.filter.category !== 'All' ? this.filter.category : null,
                availability: this.filter.availability !== 'All' ? this.filter.availability : null,
                minStock: this.filter.minStock,
                sort: this.sortField !== 'name' ? this.sortField : null,
                direction: this.sortDirection !== 'asc' ? this.sortDirection : null,
                page: this.currentPage !== 1 ? this.currentPage : null,
                size: this.pageSize !== this.pageSizeOptions[0] ? this.pageSize : null,
            },
        });
    }
};
ProductListComponent.ctorParameters = () => [
    { type: _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_3__["CatalogProductService"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_2__["ActivatedRoute"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"] }
];
ProductListComponent = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-list',
        template: `
    <div class="card">
      <div class="card-body">
        <div class="d-flex align-items-center justify-content-between mb-3">
          <div>
            <h4 class="card-title mb-1">Gestion des produits</h4>
            <p class="text-muted mb-0">Gerez les produits flash, le stock, la disponibilite et le cycle de vie.</p>
          </div>
          <div class="d-flex">
            <button type="button" class="btn btn-sm btn-primary mr-2" (click)="createProduct()">+ Nouveau produit</button>
            <button type="button" class="btn btn-sm btn-outline-primary" (click)="reload()">Recharger</button>
          </div>
        </div>

        <div class="row mb-3">
          <div class="col-md-3 mb-2 mb-md-0">
            <input
              type="text"
              class="form-control"
              placeholder="Rechercher par nom, description, categorie"
              [(ngModel)]="filter.search"
              (ngModelChange)="applyFilters()"
            />
          </div>
          <div class="col-md-2 mb-2 mb-md-0">
            <select class="form-control" [(ngModel)]="filter.category" (ngModelChange)="applyFilters()">
              <option *ngFor="let category of categories" [value]="category">{{ category }}</option>
            </select>
          </div>
          <div class="col-md-2 mb-2 mb-md-0">
            <select class="form-control" [(ngModel)]="filter.availability" (ngModelChange)="applyFilters()">
              <option value="All">Toutes disponibilites</option>
              <option value="In stock">En stock</option>
              <option value="Out of stock">Rupture</option>
            </select>
          </div>
          <div class="col-md-2 mb-2 mb-md-0">
            <input
              type="number"
              min="0"
              class="form-control"
              placeholder="Stock min"
              [(ngModel)]="filter.minStock"
              (ngModelChange)="applyFilters()"
            />
          </div>
          <div class="col-md-2">
            <button class="btn btn-light btn-block" (click)="resetFilters()">Reinitialiser</button>
          </div>
        </div>

        <div *ngIf="loading" class="text-muted">Chargement des produits...</div>
        <div *ngIf="!loading && error" class="alert alert-warning">{{ error }}</div>

        <div *ngIf="!loading && !error" class="table-responsive">
          <table class="table table-hover mb-0" *ngIf="pagedProducts.length > 0 || filteredProducts.length > 0; else noData">
            <thead>
              <tr>
                <th class="cursor-pointer" (click)="setSort('name')">Produit {{ getSortIndicator('name') }}</th>
                <th class="cursor-pointer" (click)="setSort('category')">Categorie {{ getSortIndicator('category') }}</th>
                <th class="text-right cursor-pointer" (click)="setSort('price')">Prix initial {{ getSortIndicator('price') }}</th>
                <th class="text-right cursor-pointer" (click)="setSort('finalPrice')">Prix final {{ getSortIndicator('finalPrice') }}</th>
                <th class="text-right cursor-pointer" (click)="setSort('discountPercentage')">Remise {{ getSortIndicator('discountPercentage') }}</th>
                <th class="text-center cursor-pointer" (click)="setSort('stock')">Stock {{ getSortIndicator('stock') }}</th>
                <th class="cursor-pointer" (click)="setSort('status')">Statut flash {{ getSortIndicator('status') }}</th>
                <th>Disponibilite</th>
                <th>Periode flash</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr *ngFor="let product of pagedProducts">
                <td>
                  <div class="d-flex align-items-center">
                    <img
                      *ngIf="product.images.length; else noImage"
                      [src]="product.images[0]"
                      [alt]="product.name"
                      width="42"
                      height="42"
                      class="rounded mr-2"
                      style="object-fit: cover;"
                    />
                    <ng-template #noImage>
                      <div class="bg-light text-muted rounded d-flex align-items-center justify-content-center mr-2" style="width: 42px; height: 42px; font-size: 10px;">Sans image</div>
                    </ng-template>
                    <div>
                      <div class="font-weight-bold">{{ product.name }}</div>
                      <small class="text-muted">{{ product.description }}</small>
                    </div>
                  </div>
                </td>
                <td>{{ product.category }}</td>
                <td class="text-right">{{ product.price | number: '1.2-2' }} TND</td>
                <td class="text-right font-weight-bold text-success">{{ finalPriceValue(product) | number: '1.2-2' }} TND</td>
                <td class="text-right">{{ product.discountPercentage }}%</td>
                <td class="text-center">
                  <div class="btn-group btn-group-sm" role="group">
                    <button type="button" class="btn btn-outline-secondary" (click)="decreaseStock(product)">-</button>
                    <button type="button" class="btn btn-light" disabled>{{ product.stock }}</button>
                    <button type="button" class="btn btn-outline-secondary" (click)="increaseStock(product)">+</button>
                  </div>
                </td>
                <td>
                  <span class="badge" [ngClass]="flashStatusClass(product.status)">
                    {{ flashStatusLabel(product.status) }}
                  </span>
                </td>
                <td>
                  <span class="badge" [ngClass]="product.availability === 'In stock' ? 'badge-success' : 'badge-warning text-dark'">
                    {{ availabilityLabel(product.availability) }}
                  </span>
                </td>
                <td>
                  <small class="d-block">{{ product.startDate | date: 'mediumDate' }}</small>
                  <small class="d-block">{{ product.endDate | date: 'mediumDate' }}</small>
                </td>
                <td>
                  <div class="btn-group btn-group-sm" role="group">
                    <button type="button" class="btn btn-outline-info" (click)="viewDetails(product.id)">Details</button>
                    <button type="button" class="btn btn-outline-primary" (click)="editProduct(product.id)">Modifier</button>
                    <button type="button" class="btn btn-outline-danger" (click)="deleteProduct(product.id)">Supprimer</button>
                  </div>
                </td>
              </tr>

              <tr *ngIf="!filteredProducts.length">
                <td colspan="10" class="text-center text-muted py-3">Aucun produit ne correspond a vos filtres.</td>
              </tr>
            </tbody>
          </table>
        </div>

        <div class="d-flex align-items-center justify-content-between mt-3" *ngIf="filteredProducts.length">
          <div class="text-muted">
            <span class="mr-3">Page {{ currentPage }} / {{ totalPages }}</span>
            <span>{{ filteredProducts.length }} produit(s)</span>
          </div>
          <div class="d-flex align-items-center">
            <label class="mb-0 mr-2">Lignes</label>
            <select class="form-control form-control-sm mr-2" style="width: auto;" [(ngModel)]="pageSize" (ngModelChange)="onPageSizeChange()">
              <option *ngFor="let size of pageSizeOptions" [ngValue]="size">{{ size }}</option>
            </select>
            <button class="btn btn-sm btn-light mr-1" (click)="previousPage()" [disabled]="currentPage === 1">Prec</button>
            <button
              class="btn btn-sm mr-1"
              *ngFor="let page of pageNumbers"
              [ngClass]="page === currentPage ? 'btn-primary' : 'btn-light'"
              (click)="goToPage(page)"
            >
              {{ page }}
            </button>
            <button class="btn btn-sm btn-light" (click)="nextPage()" [disabled]="currentPage === totalPages">Suiv</button>
          </div>
        </div>

        <ng-template #noData>
          <p class="card-text text-muted mb-0">Aucun produit trouve. Verifiez l'etat du gateway API et du service produit.</p>
        </ng-template>
      </div>
    </div>
  `,
    })
], ProductListComponent);



/***/ }),

/***/ "h5j1":
/*!******************************************************!*\
  !*** ./src/app/features/produits/produits.module.ts ***!
  \******************************************************/
/*! exports provided: ProduitsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProduitsModule", function() { return ProduitsModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _produits_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./produits-routing.module */ "P+VO");
/* harmony import */ var _pages_product_details_product_details_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./pages/product-details/product-details.component */ "z+1w");
/* harmony import */ var _pages_product_form_product_form_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./pages/product-form/product-form.component */ "2EGI");
/* harmony import */ var _pages_product_list_product_list_component__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./pages/product-list/product-list.component */ "TNFb");








let ProduitsModule = class ProduitsModule {
};
ProduitsModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["NgModule"])({
        declarations: [_pages_product_list_product_list_component__WEBPACK_IMPORTED_MODULE_7__["ProductListComponent"], _pages_product_details_product_details_component__WEBPACK_IMPORTED_MODULE_5__["ProductDetailsComponent"], _pages_product_form_product_form_component__WEBPACK_IMPORTED_MODULE_6__["ProductFormComponent"]],
        imports: [_angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_3__["ReactiveFormsModule"], _produits_routing_module__WEBPACK_IMPORTED_MODULE_4__["ProduitsRoutingModule"]],
    })
], ProduitsModule);



/***/ }),

/***/ "z+1w":
/*!**************************************************************************************!*\
  !*** ./src/app/features/produits/pages/product-details/product-details.component.ts ***!
  \**************************************************************************************/
/*! exports provided: ProductDetailsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProductDetailsComponent", function() { return ProductDetailsComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "mrSG");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "tyNb");
/* harmony import */ var _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../data-access/catalog-product.service */ "OvNt");




let ProductDetailsComponent = class ProductDetailsComponent {
    constructor(route, router, productsService) {
        this.route = route;
        this.router = router;
        this.productsService = productsService;
    }
    ngOnInit() {
        const id = Number(this.route.snapshot.paramMap.get('id'));
        this.productsService.getProductById(id).subscribe({
            next: (product) => (this.product = product),
            error: () => (this.product = undefined),
        });
    }
    backToList() {
        this.router.navigate(['/produits']);
    }
    edit() {
        if (this.product) {
            this.router.navigate(['/produits', this.product.id, 'edit']);
        }
    }
    availabilityLabel(availability) {
        return availability === 'In stock' ? 'En stock' : 'Rupture';
    }
};
ProductDetailsComponent.ctorParameters = () => [
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_2__["ActivatedRoute"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"] },
    { type: _data_access_catalog_product_service__WEBPACK_IMPORTED_MODULE_3__["CatalogProductService"] }
];
ProductDetailsComponent = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-product-details',
        template: `
    <section class="card" *ngIf="product; else notFound">
      <div class="card-body">
        <button type="button" class="btn btn-light btn-sm mb-3" (click)="backToList()">< Retour</button>

        <div class="d-flex align-items-center justify-content-between mb-3">
          <h4 class="card-title mb-0">{{ product.name }}</h4>
          <button type="button" class="btn btn-primary btn-sm" (click)="edit()">Modifier le produit</button>
        </div>

        <div class="row mb-3">
          <div class="col-md-4"><strong>ID:</strong> {{ product.id }}</div>
          <div class="col-md-4"><strong>Categorie:</strong> {{ product.category }}</div>
          <div class="col-md-4"><strong>Prix:</strong> {{ product.price | number: '1.2-2' }} TND</div>
          <div class="col-md-4"><strong>Remise:</strong> {{ product.discountPercentage }}%</div>
          <div class="col-md-4"><strong>Stock:</strong> {{ product.stock }}</div>
          <div class="col-md-4"><strong>Disponibilite:</strong> {{ availabilityLabel(product.availability) }}</div>
          <div class="col-md-6"><strong>Debut:</strong> {{ product.startDate | date: 'medium' }}</div>
          <div class="col-md-6"><strong>Fin:</strong> {{ product.endDate | date: 'medium' }}</div>
          <div class="col-md-6"><strong>Cree le:</strong> {{ product.createdAt | date: 'medium' }}</div>
          <div class="col-md-6"><strong>Mis a jour le:</strong> {{ product.updatedAt | date: 'medium' }}</div>
        </div>

        <h5>Description</h5>
        <p>{{ product.description }}</p>

        <h5 *ngIf="product.images.length">Images</h5>
        <div class="row" *ngIf="product.images.length">
          <div class="col-md-3 mb-3" *ngFor="let image of product.images">
            <img [src]="image" [alt]="product.name" class="img-fluid rounded border" />
          </div>
        </div>
      </div>
    </section>

    <ng-template #notFound>
      <section class="card">
        <div class="card-body">
          <p class="mb-3">Produit introuvable.</p>
          <button type="button" class="btn btn-light btn-sm" (click)="backToList()">Retour a la liste des produits</button>
        </div>
      </section>
    </ng-template>
  `,
    })
], ProductDetailsComponent);



/***/ })

}]);
//# sourceMappingURL=features-produits-produits-module-es2015.js.map