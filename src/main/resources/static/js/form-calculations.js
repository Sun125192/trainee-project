$(document).ready(function () {
    // Tự động điền Queuing Start Date khi Date Clarification được nhập  
    $("#dateClarification").on("change", function () {
        const dateClarificationValue = $(this).val();
        if (dateClarificationValue) {
            $("#queuingStartDate").val(dateClarificationValue);
        }
    });

    // Tự động điền Queuing End Date khi Effective Date được nhập  
    $("#effectiveDate").on("change", function () {
        const effectiveDateValue = $(this).val();
        if (effectiveDateValue) {
            $("#queuingEndDate").val(effectiveDateValue);
        }
    });

    // Tính Total Records khi các trường liên quan thay đổi  
    $("#numberOfFares, #rtgsRecords, #numberOfRules").on("change", function () {
        updateTotalRecords();
    });

    function updateTotalRecords() {
        const numberOfFares = parseInt($("#numberOfFares").val()) || 0;
        const rtgsRecords = parseInt($("#rtgsRecords").val()) || 0;
        const numberOfRules = parseInt($("#numberOfRules").val()) || 0;

        const totalRecords = numberOfFares + rtgsRecords + numberOfRules;
        $("#totalRecords").val(totalRecords);

        // Kiểm tra tính hợp lệ của totalRecords  
        $("#totalRecords").valid(); // Giả sử bạn đã thiết lập quy tắc hợp lệ cho trường này  
    }
});