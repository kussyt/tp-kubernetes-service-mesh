const sampleForm = document.getElementById("sample-form");
const sampleResult = document.getElementById("sample-result");
const sampleIdInput = document.getElementById("sampleId");
const analyzeBtn = document.getElementById("analyze-btn");
const analysisResult = document.getElementById("analysis-result");

function showOutput(element, data) {
    element.textContent = typeof data === "string" ? data : JSON.stringify(data, null, 2);
}

async function parseResponse(response) {
    const text = await response.text();
    try {
        return { data: JSON.parse(text), raw: text };
    } catch {
        return { data: null, raw: text };
    }
}

sampleForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const body = {
        patientName: document.getElementById("patientName").value.trim(),
        examType: document.getElementById("examType").value,
        sampleType: document.getElementById("sampleType").value.trim()
    };

    if (!body.patientName || !body.sampleType) {
        showOutput(sampleResult, "Renseignez le nom du patient et le type de prélèvement.");
        return;
    }

    try {
        const response = await fetch("/api/samples", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });
        const { data, raw } = await parseResponse(response);
        if (!response.ok) {
            showOutput(sampleResult, data || ("Erreur HTTP " + response.status + " : " + raw));
            return;
        }
        showOutput(sampleResult, data);
        sampleIdInput.value = data.id;
    } catch (error) {
        showOutput(sampleResult, "Erreur réseau : " + error.message);
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
        const { data, raw } = await parseResponse(response);
        if (!response.ok) {
            showOutput(analysisResult, data || ("Erreur HTTP " + response.status + " : " + raw));
            return;
        }
        showOutput(analysisResult, data);
    } catch (error) {
        showOutput(analysisResult, "Erreur réseau : " + error.message);
    } finally {
        analyzeBtn.disabled = false;
    }
});
