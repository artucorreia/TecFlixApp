# Etapa 1: Build da aplicação Angular
FROM node:22-alpine AS build

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto Angular para dentro da imagem
COPY package*.json .

RUN npm install --silent

# RUN npm install -g @angular/cli

COPY . .

# Realizar o build de produção do Angular
# RUN ng build -c production
RUN npx ng build -c production



# Etapa Final: Exportar apenas o build
FROM alpine:latest AS final

# Diretório de saída configurado como volume em docker-compose
WORKDIR /output
COPY --from=build /app/dist/ /output/