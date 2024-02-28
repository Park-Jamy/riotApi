function searchByName() {
    var name = document.getElementById("name").value;

    if (name.trim() !== '') {
        window.location.href = '/riot/summoner/' + encodeURIComponent(name);
    } else {
        alert('검색어 입력해');
    }
}