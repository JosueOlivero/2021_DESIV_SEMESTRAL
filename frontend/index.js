(() => {
    const experiencesContainer = document.querySelector('.page-content-experiences');
    const getExperiences = async (event) => {
        const experiencesRaw = await fetch('http://localhost:4567/experiences');
        const { group } = await experiencesRaw.json();
        group.forEach(({ title, workPlace, workDescription }) => {
            const template = `<div class="page-content-element-experience"><div class="dot-container"><span class="dot"></span></div><div class="exp-info-container"><h1 class="info-title">${title}</h1><h2>${workPlace}</h2></div></div><div class="experience-description"><p>${workDescription}</p></div>`
            experiencesContainer.innerHTML += template;
        });
    };

    const skillsContainer = document.querySelector('.page-content-skills');
    const getSkills = async (event) => {
        const skillsRaw = await fetch('http://localhost:4567/skills');
        const { group } = await skillsRaw.json();
        group.forEach(({ name }) => {
            const template = `<div class="page-content-element-skill"><label class="skill-label">${name}</label></div>`
            skillsContainer.innerHTML += template;
        });
    };

    const recommendationsContainer = document.querySelector('.page-content-recommendations');
    const getRecommendations = async (event) => {
        const recommendationsRaw = await fetch('http://localhost:4567/recommendations');
        const { group } = await recommendationsRaw.json();
        group.forEach(({ name, title, workPlace, recommendation }) => {
            const template = `<div class="page-content-element-recommendation"></div><div class="recommendation-info-container"><div><h1 class="info-title">${name}</h1><h2>${title}</h2 id="rec-h2"><h3 id="rec-h3">${workPlace}</h3></div><div class="dot-container"><span class="dot"></span></div></div><div><p>${recommendation}</p></div>`
            recommendationsContainer.innerHTML += template;
        });
    };

    const loadInfo = () => {
        getExperiences()
        getSkills()
        getRecommendations()
    }
    window.addEventListener('load', loadInfo);
})();