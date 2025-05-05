$(document).ready(function() {
	// *** 1. Định nghĩa các phương thức kiểm tra tùy chỉnh ***
	// Phương thức kiểm tra ít nhất một trường có giá trị
	$.validator.addMethod(
		"atLeastOneRequired",
		function(value, element, param) {
			var fields = param.split(","); // Tách các trường cần kiểm tra
			var isValid = false;
			$(fields).each(function(index, field) {
				if ($(field).val()) {
					isValid = true;
				}
			});
			return isValid;
		},
		"Please select at least one field to filter." // Thông báo lỗi
	);

	// Phương thức kiểm tra ngày (Discontinue Date >= Effective Date)
	$.validator.addMethod(
		"dateGreaterThanOrEqual",
		function(value, element, params) {
			var effectiveDate = $(params).val();
			if (!value || !effectiveDate) return true; // Bỏ qua nếu một trong hai giá trị rỗng
			var effectiveDateObj = new Date(effectiveDate);
			var discontinueDateObj = new Date(value);
			if (isNaN(discontinueDateObj.getTime()) || isNaN(effectiveDateObj.getTime())) {
				return false; // Ngày không hợp lệ
			}
			return discontinueDateObj >= effectiveDateObj; // Kiểm tra điều kiện
		},
		"Discontinue Date must be later than or equals to Effective Date."
	);

	// Phương thức kiểm tra chỉ cho phép chữ cái và số
	$.validator.addMethod(
		"lettersOnly",
		function(value, element) {
			return this.optional(element) || /^[A-Za-z0-9]+$/.test(value); // Không cho phép khoảng trắng
		},
		"Please enter only letters (no spaces allowed)."
	);

	// *** 2. Khởi tạo và cấu hình thư viện jQuery Validation ***
	$("form").validate({
		rules: {
			market: { required: true },
			airline: { required: true, lettersOnly: true },
			contractNumber: { lettersOnly: true },
			discontinueDate: {
				required: false,
				dateISO: true,
				dateGreaterThanOrEqual: "#effectiveDate"
			},
			effectiveDate: { required: false, dateISO: true },
			dateReceived: { required: false, dateISO: true },
		},
		messages: {
			market: "Please select Market to filter.",
			airline: "Please select Airline to filter.",
			discontinueDate: {
				dateISO: "Discontinue Date must be a Date type.",
				dateGreaterThanOrEqual: "Discontinue Date must be later than or equals to Effective Date.",
			},
			effectiveDate: "Effective Date must be a Date type.",
			dateReceived: "Date Received must be a Date type.",
		},
		highlight: function(element) {
			$(element).addClass('is-invalid'); // Thêm class lỗi
			$("#" + element.id + "-error").show(); // Hiện thông báo lỗi
		},
		unhighlight: function(element) {
			$(element).removeClass('is-invalid'); // Xóa class lỗi
			$("#" + element.id + "-error").hide(); // Ẩn thông báo lỗi
		},
		submitHandler: function(form) {
			form.submit();
		},
	});

	// *** 3. Khởi tạo Select2 cho các trường dữ liệu có chọn lọc ***
	$('#market, #airline, #contractNumber, #fareType').select2({
		placeholder: "",
		allowClear: true,
	});

	// *** 4. Hàm kiểm tra và kích hoạt lại validate khi có thay đổi giá trị ***
	function validateOnChange(field1, field2) {
		$(field1).on('change', function() {
			$(field2).valid(); // Kiểm tra tính hợp lệ của trường
		});
	}
	// Gọi hàm kiểm tra validate
	validateOnChange('#effectiveDate', '#discontinueDate');
	validateOnChange('#market', '#market');
	validateOnChange('#airline', '#airline');

	// *** 5. Lắng nghe sự kiện thay đổi trên Market và Airline để tải dữ liệu ***
	$('#market, #airline').change(function() {
		var marketId = $('#market').val();   // Lấy giá trị của Market
		var airlineId = $('#airline').val(); // Lấy giá trị của Airline

		// Gửi AJAX request để lấy danh sách Contract Numbers và Fare Types
		$.ajax({
			url: '/market-and-airline',
			type: 'GET',
			data: { market: marketId, airline: airlineId },
			success: function(response) {
				// Cập nhật danh sách Contract Numbers
				var contractDropdown = $('#contractNumber');
				contractDropdown.empty().append('<option value=""> </option>');
				response.contractNumbers.forEach(function(contract) {
					contractDropdown.append('<option value="' + contract.value + '">' + contract.text + '</option>');
				});

				// Cập nhật danh sách Fare Types
				var fareTypeDropdown = $('#fareType');
				fareTypeDropdown.empty().append('<option value=""> </option>');
				response.fareTypes.forEach(function(fareType) {
					fareTypeDropdown.append('<option value="' + fareType.value + '">' + fareType.text + '</option>');
				});
			},
			error: function(xhr, status, error) {
				console.error('Lỗi khi tải dữ liệu!', error);
			}
		});
	});
});
