function makeEditable(table) {

    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $/*('#filter').click(function () {
        $.ajax({
            url: ajaxUrl + id,
            type: 'DELETE',
            success: function () {
                updateTable();
                successNoty('Deleted');
            }
        });
    });*/

    $('.delete').click(function () {

        deleteRow($(this).closest('tr').attr('id'), table);
    });

    $('#detailsForm').submit(function () {
        save(table);
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id, table) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable(table);
            successNoty('Deleted');
        }
    });
}

function updateTable(table) {
    $.get(ajaxUrl, function (data) {
        table.clear();
        $.each(data, function (key, item) {
            table.row.add(item);
        });
        table.draw();
    });
}

function save(table) {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable(table);
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });


    function makeEditableUser() {
        $("input[type='checkbox']").change(function () {
            var thisCheck = $(this);

            if (thisCheck.is(':checked')) {
                window.alert(thisCheck.attr("id"));
            }
        });
    }

}




