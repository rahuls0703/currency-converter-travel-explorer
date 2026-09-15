const API_BASE = ""; // same origin, since frontend is served by Spring Boot itself

// ---------- Toast helper ----------
function showToast(message, isError = true) {
  const container = document.getElementById("toastContainer");
  const toast = document.createElement("div");
  toast.className = "toast";
  toast.innerHTML = `<span>${isError ? "⚠️" : "✅"}</span><span>${message}</span>`;
  container.appendChild(toast);
  setTimeout(() => {
    toast.style.opacity = "0";
    toast.style.transition = "opacity 0.3s ease";
    setTimeout(() => toast.remove(), 300);
  }, 4000);
}

// ---------- Simple conversion ----------
document.getElementById("convertBtn").addEventListener("click", async () => {
  const amount = document.getElementById("amount").value;
  const from = document.getElementById("fromCurrency").value;
  const to = document.getElementById("toCurrency").value;
  const resultDiv = document.getElementById("convertResult");

  resultDiv.textContent = "Converting...";

  try {
    const res = await fetch(`${API_BASE}/api/convert?from=${from}&to=${to}&amount=${amount}`);
    if (!res.ok) throw new Error("Conversion failed");
    const data = await res.json();
    resultDiv.textContent =
      `${data.originalAmount.toLocaleString()} ${data.from} = ${data.convertedAmount.toFixed(2)} ${data.to}`;
  } catch (err) {
    resultDiv.textContent = "";
    showToast("Couldn't fetch that conversion. Please try again.");
    console.error(err);
  }
});

// ---------- Skeleton loaders ----------
function renderSkeletons(count = 6) {
  const skeletonGrid = document.getElementById("skeletonGrid");
  const travelResults = document.getElementById("travelResults");
  travelResults.innerHTML = "";
  skeletonGrid.innerHTML = "";
  skeletonGrid.classList.remove("hidden");

  for (let i = 0; i < count; i++) {
    const card = document.createElement("div");
    card.className = "skeleton-card";
    card.innerHTML = `
      <div class="skeleton-line" style="width: 60%; height: 20px;"></div>
      <div class="skeleton-line" style="width: 40%;"></div>
      <div class="skeleton-line" style="width: 50%; height: 28px; margin-top: 14px;"></div>
      <div class="skeleton-line" style="width: 90%;"></div>
      <div class="skeleton-line" style="width: 80%;"></div>
    `;
    skeletonGrid.appendChild(card);
  }
}

function clearSkeletons() {
  const skeletonGrid = document.getElementById("skeletonGrid");
  skeletonGrid.classList.add("hidden");
  skeletonGrid.innerHTML = "";
}

// ---------- Detail modal ----------
function openDetailModal(dest) {
  const modal = document.getElementById("detailModal");
  const content = document.getElementById("modalContent");
  content.innerHTML = `
    <button id="closeModalBtn" class="absolute top-4 right-4 text-surface-muted hover:text-white text-xl leading-none">&times;</button>
    <h3 class="font-heading text-2xl font-bold text-white mb-1">${dest.countryName}</h3>
    <p class="text-sm text-surface-muted mb-4">${dest.region} · ${dest.currencyCode}</p>

    <div class="mb-4">
      <h4 class="font-heading font-semibold text-ocean-600 text-sm uppercase tracking-wide mb-1">Top Spots</h4>
      <p class="text-surface-text/90 text-sm leading-relaxed">${dest.attractions}</p>
    </div>

    <div>
      <h4 class="font-heading font-semibold text-ocean-600 text-sm uppercase tracking-wide mb-1">Culture</h4>
      <p class="text-surface-text/90 text-sm leading-relaxed">${dest.cultureHighlights}</p>
    </div>
  `;
  modal.classList.remove("hidden");
  modal.classList.add("flex");

  document.getElementById("closeModalBtn").addEventListener("click", closeDetailModal);
}

function closeDetailModal() {
  const modal = document.getElementById("detailModal");
  modal.classList.add("hidden");
  modal.classList.remove("flex");
}

document.getElementById("detailModal").addEventListener("click", (e) => {
  if (e.target.id === "detailModal") closeDetailModal();
});

// ---------- Travel affordability explorer ----------
document.getElementById("exploreBtn").addEventListener("click", async () => {
  const amount = document.getElementById("travelAmount").value;
  const currency = document.getElementById("travelCurrency").value;
  const resultsGrid = document.getElementById("travelResults");

  resultsGrid.innerHTML = "";
  renderSkeletons(6);

  try {
    const res = await fetch(`${API_BASE}/api/travel-recommendations?from=${currency}&amount=${amount}`);
    if (!res.ok) throw new Error("Failed to fetch recommendations");
    const destinations = await res.json();

    clearSkeletons();

    if (destinations.length === 0) {
      resultsGrid.innerHTML = `<p class="text-slate-500 col-span-full text-center py-10">No destinations found for that amount.</p>`;
      return;
    }

    destinations.forEach((dest) => {
      const card = document.createElement("div");
      card.className = "dest-card";
      card.innerHTML = `
        <h3>${dest.countryName}</h3>
        <div class="dest-region">📍 ${dest.region} · ${dest.currencyCode}</div>

        <div class="days-pill">
          <span>~${Math.floor(dest.estimatedDaysMid)}</span>
          <span class="label">days</span>
        </div>

        <div class="budget-row">
          <span>Mid-range budget</span>
          <span class="val">${Math.floor(dest.estimatedDaysMid)} days</span>
        </div>
        <div class="budget-row">
          <span>Tight budget</span>
          <span class="val">${Math.floor(dest.estimatedDaysBudget)} days</span>
        </div>

        <div class="converted-amount">≈ ${dest.convertedAmount.toLocaleString()} ${dest.currencyCode}</div>
        <button class="details-btn" data-country="${dest.countryName}">View top spots &amp; culture →</button>
      `;
      card.querySelector(".details-btn").addEventListener("click", () => openDetailModal(dest));
      resultsGrid.appendChild(card);
    });
  } catch (err) {
    clearSkeletons();
    showToast("Couldn't fetch travel recommendations. Please try again.");
    console.error(err);
  }
});
