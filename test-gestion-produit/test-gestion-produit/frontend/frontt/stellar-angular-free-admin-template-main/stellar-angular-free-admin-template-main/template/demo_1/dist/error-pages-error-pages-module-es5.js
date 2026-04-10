(function () {
  function _classCallCheck(a, n) { if (!(a instanceof n)) throw new TypeError("Cannot call a class as a function"); }

  function _defineProperties(e, r) { for (var t = 0; t < r.length; t++) { var o = r[t]; o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(e, _toPropertyKey(o.key), o); } }

  function _createClass(e, r, t) { return r && _defineProperties(e.prototype, r), t && _defineProperties(e, t), Object.defineProperty(e, "prototype", { writable: !1 }), e; }

  function _toPropertyKey(t) { var i = _toPrimitive(t, "string"); return "symbol" == typeof i ? i : i + ""; }

  function _toPrimitive(t, r) { if ("object" != typeof t || !t) return t; var e = t[Symbol.toPrimitive]; if (void 0 !== e) { var i = e.call(t, r || "default"); if ("object" != typeof i) return i; throw new TypeError("@@toPrimitive must return a primitive value."); } return ("string" === r ? String : Number)(t); }

  (window["webpackJsonp"] = window["webpackJsonp"] || []).push([["error-pages-error-pages-module"], {
    /***/
    "6tXz":
    /*!****************************************************************************************************!*\
      !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/error-pages/error500/error500.component.html ***!
      \****************************************************************************************************/

    /*! exports provided: default */

    /***/
    function tXz(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony default export */


      __webpack_exports__["default"] = "<div class=\"d-flex align-items-center text-center error-page bg-info pt-5 pb-4 h-100\">\n  <div class=\"row flex-grow\">\n    <div class=\"col-lg-7 mx-auto text-white\">\n      <div class=\"row align-items-center d-flex flex-row\">\n        <div class=\"col-lg-6 text-lg-right pr-lg-4\">\n          <h1 class=\"display-1 mb-0\">500</h1>\n        </div>\n        <div class=\"col-lg-6 error-page-divider text-lg-left pl-lg-4\">\n          <h2>SORRY!</h2>\n          <h3 class=\"font-weight-light\">Internal server error!</h3>\n        </div>\n      </div>\n      <div class=\"row mt-5\">\n        <div class=\"col-12 text-center mt-xl-2\">\n          <a class=\"text-white font-weight-medium\" routerLink=\"/dashboard\">Back to home</a>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>  ";
      /***/
    },

    /***/
    "CRg8":
    /*!************************************************************!*\
      !*** ./src/app/error-pages/error404/error404.component.ts ***!
      \************************************************************/

    /*! exports provided: Error404Component */

    /***/
    function CRg8(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "Error404Component", function () {
        return Error404Component;
      });
      /* harmony import */


      var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! tslib */
      "mrSG");
      /* harmony import */


      var _raw_loader_error404_component_html__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! raw-loader!./error404.component.html */
      "DuIw");
      /* harmony import */


      var _error404_component_scss__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./error404.component.scss */
      "yqal");
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var Error404Component = /*#__PURE__*/function () {
        function Error404Component() {
          _classCallCheck(this, Error404Component);
        }

        return _createClass(Error404Component, [{
          key: "ngOnInit",
          value: function ngOnInit() {}
        }]);
      }();

      Error404Component.ctorParameters = function () {
        return [];
      };

      Error404Component = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["Component"])({
        selector: 'app-error404',
        template: _raw_loader_error404_component_html__WEBPACK_IMPORTED_MODULE_1__["default"],
        styles: [_error404_component_scss__WEBPACK_IMPORTED_MODULE_2__["default"]]
      })], Error404Component);
      /***/
    },

    /***/
    "DuIw":
    /*!****************************************************************************************************!*\
      !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/error-pages/error404/error404.component.html ***!
      \****************************************************************************************************/

    /*! exports provided: default */

    /***/
    function DuIw(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony default export */


      __webpack_exports__["default"] = "<div class=\"d-flex align-items-center text-center error-page bg-primary pt-5 pb-4 h-100\">\n  <div class=\"row flex-grow\">\n    <div class=\"col-lg-8 mx-auto text-white\">\n      <div class=\"row align-items-center d-flex flex-row\">\n        <div class=\"col-lg-6 text-lg-right pr-lg-4\">\n          <h1 class=\"display-1 mb-0\">404</h1>\n        </div>\n        <div class=\"col-lg-6 error-page-divider text-lg-left pl-lg-4\">\n          <h2>SORRY!</h2>\n          <h3 class=\"font-weight-light\">The page you’re looking for was not found.</h3>\n        </div>\n      </div>\n      <div class=\"row mt-5\">\n        <div class=\"col-12 text-center mt-xl-2\">\n          <a class=\"text-white font-weight-medium\" routerLink=\"/dashboard\">Back to home</a>\n        </div>\n      </div>\n    </div>\n  </div>\n</div>";
      /***/
    },

    /***/
    "ECuu":
    /*!**************************************************************!*\
      !*** ./src/app/error-pages/error500/error500.component.scss ***!
      \**************************************************************/

    /*! exports provided: default */

    /***/
    function ECuu(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony default export */


      __webpack_exports__["default"] = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Vycm9yLXBhZ2VzL2Vycm9yNTAwL2Vycm9yNTAwLmNvbXBvbmVudC5zY3NzIn0= */";
      /***/
    },

    /***/
    "dflm":
    /*!***************************************************!*\
      !*** ./src/app/error-pages/error-pages.module.ts ***!
      \***************************************************/

    /*! exports provided: ErrorPagesModule */

    /***/
    function dflm(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "ErrorPagesModule", function () {
        return ErrorPagesModule;
      });
      /* harmony import */


      var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! tslib */
      "mrSG");
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");
      /* harmony import */


      var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! @angular/common */
      "ofXK");
      /* harmony import */


      var _error404_error404_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! ./error404/error404.component */
      "CRg8");
      /* harmony import */


      var _error500_error500_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(
      /*! ./error500/error500.component */
      "klF6");
      /* harmony import */


      var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(
      /*! @angular/router */
      "tyNb");

      var routes = [{
        path: '404',
        component: _error404_error404_component__WEBPACK_IMPORTED_MODULE_3__["Error404Component"]
      }, {
        path: '500',
        component: _error500_error500_component__WEBPACK_IMPORTED_MODULE_4__["Error500Component"]
      }];

      var ErrorPagesModule = /*#__PURE__*/_createClass(function ErrorPagesModule() {
        _classCallCheck(this, ErrorPagesModule);
      });

      ErrorPagesModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        declarations: [_error404_error404_component__WEBPACK_IMPORTED_MODULE_3__["Error404Component"], _error500_error500_component__WEBPACK_IMPORTED_MODULE_4__["Error500Component"]],
        imports: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"], _angular_router__WEBPACK_IMPORTED_MODULE_5__["RouterModule"].forChild(routes)]
      })], ErrorPagesModule);
      /***/
    },

    /***/
    "klF6":
    /*!************************************************************!*\
      !*** ./src/app/error-pages/error500/error500.component.ts ***!
      \************************************************************/

    /*! exports provided: Error500Component */

    /***/
    function klF6(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony export (binding) */


      __webpack_require__.d(__webpack_exports__, "Error500Component", function () {
        return Error500Component;
      });
      /* harmony import */


      var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(
      /*! tslib */
      "mrSG");
      /* harmony import */


      var _raw_loader_error500_component_html__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(
      /*! raw-loader!./error500.component.html */
      "6tXz");
      /* harmony import */


      var _error500_component_scss__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(
      /*! ./error500.component.scss */
      "ECuu");
      /* harmony import */


      var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(
      /*! @angular/core */
      "fXoL");

      var Error500Component = /*#__PURE__*/function () {
        function Error500Component() {
          _classCallCheck(this, Error500Component);
        }

        return _createClass(Error500Component, [{
          key: "ngOnInit",
          value: function ngOnInit() {}
        }]);
      }();

      Error500Component.ctorParameters = function () {
        return [];
      };

      Error500Component = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["Component"])({
        selector: 'app-error500',
        template: _raw_loader_error500_component_html__WEBPACK_IMPORTED_MODULE_1__["default"],
        styles: [_error500_component_scss__WEBPACK_IMPORTED_MODULE_2__["default"]]
      })], Error500Component);
      /***/
    },

    /***/
    "yqal":
    /*!**************************************************************!*\
      !*** ./src/app/error-pages/error404/error404.component.scss ***!
      \**************************************************************/

    /*! exports provided: default */

    /***/
    function yqal(module, __webpack_exports__, __webpack_require__) {
      "use strict";

      __webpack_require__.r(__webpack_exports__);
      /* harmony default export */


      __webpack_exports__["default"] = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2Vycm9yLXBhZ2VzL2Vycm9yNDA0L2Vycm9yNDA0LmNvbXBvbmVudC5zY3NzIn0= */";
      /***/
    }
  }]);
})();
//# sourceMappingURL=error-pages-error-pages-module-es5.js.map