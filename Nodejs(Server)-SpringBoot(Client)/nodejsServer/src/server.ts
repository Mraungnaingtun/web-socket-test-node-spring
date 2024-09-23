// src/server.ts
import express from 'express';
import http from 'http';
import WebSocket from 'ws';

// Create an Express app
const app = express();

// Create an HTTP server
const server = http.createServer(app);

// Create a WebSocket server
const wss = new WebSocket.Server({ server });

// WebSocket connection
wss.on('connection', (ws: WebSocket) => {
    console.log('New client connected');

    // When a message is received from a client
    ws.on('message', (message: WebSocket.Data) => {
        console.log(`Received: ${message}`);

        // Broadcast the message to all clients
        wss.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(message);
            }
        });
    });

    // Handle client disconnection
    ws.on('close', () => {
        console.log('Client disconnected');
    });
});

// Start the server
const PORT = process.env.PORT || 8080;
server.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});
