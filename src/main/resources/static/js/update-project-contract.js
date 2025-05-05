$(document).ready(function() {

	// Hàm xử lý quay lại trang trước đó
	$("#cancelButton").on("click", function() {
		window.history.back(); // Quay lại trang trước đó trong lịch sử trình duyệt
	});

	// Khi nút Clear được nhấn
	$("#clearButton").click(function() {
		// Lấy tất cả các input, select, textarea trong form và đặt giá trị rỗng
		$("#projectForm").find("input, select, textarea").val("").trigger("change");
	});

	// Thêm phương thức kiểm tra ngày lớn hơn hoặc bằng  
	$.validator.addMethod(
		"dateGreaterOrEqual",
		function(value, element, params) {
			if (!value || !$(params).val()) return true; // Không kiểm tra nếu một trong hai trường trống  
			return new Date(value) >= new Date($(params).val()); // Kiểm tra ngày hiện tại có lớn hơn hoặc bằng ngày tham chiếu không
		},
		"" // Tin nhắn lỗi sẽ được thêm trong phần messages  
	);

	// Thêm phương thức thứ hai để kiểm tra ngày khác  
	$.validator.addMethod(
		"dateGreaterOrEqualExtra",
		function(value, element, params) {
			if (!value || !$(params).val()) return true; // Không kiểm tra nếu một trong hai trường trống  
			return new Date(value) >= new Date($(params).val()); // Kiểm tra ngày hiện tại có lớn hơn hoặc bằng ngày tham chiếu không
		},
		"" // Tin nhắn lỗi sẽ được thêm trong phần messages  
	);

	// Kiểm tra trạng thái hợp đồng và vô hiệu hóa các trường nếu cần  
	const contractStatus = $("#contractStatus").val(); // Giả sử trạng thái hợp đồng lưu trong "contractStatus"  

	if (contractStatus === "completed" || contractStatus === "terminated") {
		// Vô hiệu hóa tất cả các trường nhập liệu, ngoại trừ nút Cancel  
		$("form :input").not("#cancelButton").prop("disabled", true);

		// Ẩn nút "Update" nếu tồn tại  
		if ($("#updateButton").length) {
			$("#updateButton").hide();
		}

		// Ẩn nút "Clear" nếu tồn tại  
		if ($("#clearButton").length) {
			$("#clearButton").hide();
		}
	} else {
		// Bật lại tất cả các trường nhập liệu, ngoại trừ nút Cancel  
		$("form :input").not("#cancelButton").prop("disabled", false);

		// Hiển thị nút "Update" nếu tồn tại  
		if ($("#updateButton").length) {
			$("#updateButton").show();
		}

		// Hiển thị nút "Clear" nếu tồn tại  
		if ($("#clearButton").length) {
			$("#clearButton").show();
		}
	}

	// Lưu giá trị ban đầu của "Important Level" khi dropdown được focus  
	$("#importantLevel").on("focus", function() {
		$(this).data("previousValue", $(this).val()); // Lưu giá trị trước khi người dùng chọn
	});

	// Kiểm tra trạng thái hợp đồng khi thay đổi "Important Level"  
	$("#importantLevel").on("change", function() {
		const contractStatus = $("#contractStatus").val(); // Lấy trạng thái hợp đồng  
		const previousValue = $(this).data("previousValue"); // Lấy giá trị trước khi thay đổi dropdown  

		if (contractStatus === "active") {
			// Tạo một thông báo với hai nút OK và Cancel  
			const confirmationMessage = confirm(
				"You are not allowed to update Important Level of an active contract. Please terminate the contract first, then create a new contract with the new Important Level."
			);

			// Kiểm tra xem người dùng nhấn OK hay Cancel  
			if (confirmationMessage) {
				// Nếu người dùng chọn OK, chuyển đến màn hình cập nhật để hủy hợp đồng  
				// Bạn có thể thêm mã để chuyển hướng đến màn hình mới ở đây  
				$(this).val(previousValue); // Đặt lại giá trị ban đầu  
				$(this).data("previousValue", $(this).val());
				alert("screen of Active Project/Contract schedule and terminate contract - Update")
			} else {
				// Nếu người dùng chọn Cancel, quay lại trang filter-project-contract.html 
				$(this).val(previousValue); // Đặt lại giá trị ban đầu    
			}
		}
	});
	
	$.validator.addMethod("pattern", function(value, element, regexpr) {
	    return this.optional(element) || regexpr.test(value);
	}, "Please enter a valid value.");

	// Xác thực biểu mẫu  
	$("form").validate({
		rules: {
			contractNumber: {
				required: true,
				 pattern: /^[A-Za-z0-9\-]+$/, // Thử sử dụng 'regex' thay vì 'pattern'
				 maxlength: 20, // Giới hạn tối đa 20 ký tự
			},
			airlineCode: {
				required: true,
			},
			importantLevel: {
				required: true,
			},
			region: {
				required: true,
			},
			numberOfFareSheets: {
				required: true,
				number: true,
				maxlength: 3,
				min: 1,
			},
			rtgsActual: {
				required: true,
				number: true,
				maxlength: 5,
				min: 1,
			},
			numberOfRules: {
				required: true,
				number: true,
				maxlength: 3,
				min: 1,
			},
			contractName: {
				required: true,
				noSpaces: true,
			},
			countryCode: {
				required: true,
			},
			priority: {
				required: true,
			},
			fareType: {
				required: true,
			},
			numberOfFares: {
				required: true,
				number: true,
				maxlength: 5,
				min: 1,
			},
			rtgsRecords: {
				required: true,
				number: true,
				maxlength: 5,
				min: 1,
			},
			totalRecords: {
				required: true,
				number: true,
				maxlength: 8,
			},
			effectiveDate: {
				required: true,
				date: true,
			},
			dateClarificationCleared: {
				required: true,
				date: true,
				dateGreaterOrEqual: "#dateReceived", // Kiểm tra với Date Received
			},
			dateReceived: {
				required: true,
				date: true,
			},
			discontinueDate: {
				required: true,
				date: true,
				dateGreaterOrEqual: "#effectiveDate", // Kiểm tra với Effective Date
				dateGreaterOrEqualExtra: "#dateClarificationCleared", // Kiểm tra với Date Clarification
			},
		},
		messages: {
			contractNumber: {
				required: "Contract Number must be entered.",
				pattern: "Contract Number must be in the format of alpha numeric.",
				maxlength: "Contract Number cannot exceed 20 characters",
			},
			airlineCode: {
				required: "Airline Code must be entered.",
			},
			importantLevel: {
				required: "Important Level must be entered.",
			},
			region: {
				required: "Region must be entered.",
			},
			numberOfFareSheets: {
				required: "Number of Fare Sheets must be entered.",
				number: "Number of Fare Sheets must be entered.",
				maxlength: "Number of Fare Sheets cannot exceed 3 digits",
				min: "Number of Fare Sheets must be a number and > 0.",
			},
			rtgsActual: {
				required: "Rtgs Actual must be entered.",
				number: "Rtgs Actual must be entered.",
				maxlength: "Rtgs Actual cannot exceed 5 digits",
				min: "Rtgs Actual must be a number and > 0.",
			},
			numberOfRules: {
				required: "Number of Rules must be entered.",
				number: "Number of Rules must be entered.",
				maxlength: "Number of Rules cannot exceed 3 digits",
				min: "Number of Rules must be a number and > 0.",
			},
			contractName: {
				required: "Contract Name must be entered.",
			},
			countryCode: {
				required: "Market is required.",
			},
			priority: {
				required: "Priority is required.",
			},
			fareType: {
				required: "Fare Type is required.",
			},
			numberOfFares: {
				required: "Number of Fares must be entered.",
				number: "Please enter a valid number",
				maxlength: "Number of Fares cannot exceed 5 digits",
				min: "Number of Fares must be a number and > 0.",
			},
			rtgsRecords: {
				required: "Rtgs Records must be entered.",
				number: "Please enter a valid number",
				maxlength: "Rtgs Records cannot exceed 5 digits",
				min: "Rtgs Records must be a number and > 0.",
			},
			totalRecords: {
				required: "Total Records must be entered.",
				number: "Please enter a valid number",
				maxlength: "Total Records cannot exceed 8 digits",
			},
			effectiveDate: {
				required: "Effective Date must be entered.",
				date: "Effective Date must be a Date type.",
			},
			dateClarificationCleared: {
				required: "Date Clarification Cleared must be entered.",
				date: "ContractDate Clarification Cleared must be a Date type.",
				dateGreaterOrEqual:
					"Date Clarification must be later than or equals to Date Received.",
			},
			dateReceived: {
				required: "Date Received must be entered.",
				date: "Date Received must be a Date type.",
			},
			discontinueDate: {
				required: "Discontinue Date must be entered.",
				date: "Discontinue Date must be a Date type.",
				dateGreaterOrEqual:
					"Discontinue Date must be later than or equals to Effective Date.",
				dateGreaterOrEqualExtra:
					"Discontinue Date must be later than or equals to Date Clarification.",
			},
		},
		highlight: function(element) {
			$(element).addClass("is-invalid"); // Chỉ cần thêm label này
		},
		unhighlight: function(element) {
			$(element).removeClass("is-invalid");
			$("#" + element.id + "-error").hide(); // Ẩn thông báo lỗi nếu hợp lệ
		},
		submitHandler: function(form) {
			alert("Form submitted successfully!");
			form.reset();
		},
	});

	$("#effectiveDate").on("change", function() {
		// Kiểm tra lại trường discontinueDate
		$("#effectiveDate").valid(); // Gọi valid để kiểm tra lỗi
	});

	$("#dateClarificationCleared").on("change", function() {
		// Kiểm tra lại trường discontinueDate
		$("#dateClarification").valid(); // Gọi valid để kiểm tra lỗi
	});
	$("#dateReceived").on("change", function() {
		// Kiểm tra lại trường discontinueDate
		$("#dateReceived").valid(); // Gọi valid để kiểm tra lỗi
	});

});
