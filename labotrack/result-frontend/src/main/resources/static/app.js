const sampleForm = document.getElementById("sample-form");
const sampleResult = document.getElementById("sample-result");
const sampleIdInput = document.getElementById("sampleId");
const analyzeBtn = document.getElementById("analyze-btn");
const analysisResult = document.getElementById("analysis-result");

function showOutput(element, data) {
    element.textContent = typeof data === "string" ? data : JSON.stringify(data, null, 2);
}

sampleForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const body = {
        patientName: document.getElementById("patientName").value.trim(),
        examType: document.getElementById("examType").value,
        sampleType: document.getElementById("sampleType").value.trim()
    };

    try {
        const response = await fetch("/api/samples", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });
        const data = await response.json();
        if (!response.ok) {
            showOutput(sampleResult, data);
            return;
        }
        showOutput(sampleResult, data);
        sampleIdInput.value = data.id;
    } catch (error) {
        showOutput(sampleResult, "Erreur : " + error.message);
    }
});

analyzeBtn.addEventListener("click", async () => {
    const id = sampleIdInput.value;
    if (!id) {
        showOutput(analysisResult, "Indiquez un identifiant d'échantillon.");
        return;
    }

    analyzeBtn.disabled = true;
    showOutput(analysisResult, "Analyse en cours…");

    try {
        const response = await fetch("/api/analyze/" + id, { method: "POST" });
        const data = await response.json();
        showOutput(analysisResult, data);
    } catch (error) {
        showOutput(analysisResult, "Erreur : " + error.message);
    } finally {
        analyzeBtn.disabled = false;
    }
});
