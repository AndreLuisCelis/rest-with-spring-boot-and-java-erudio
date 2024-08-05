# Usar uma imagem oficial do Node.js como a imagem base
FROM node:14 AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo package.json e package-lock.json
COPY package*.json ./

# Instalar as dependências
RUN npm install

# Copiar o restante da aplicação
COPY . .

# Construir a aplicação
RUN npm run build

# Usar uma imagem oficial do Nginx para servir a aplicação
FROM nginx:alpine

# Copiar os arquivos construídos para o diretório padrão do Nginx
COPY --from=build /app/build /usr/share/nginx/html

COPY ./nginx.conf ./conf.d/default.conf

# Expor a porta que o Nginx usará
EXPOSE 80

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]