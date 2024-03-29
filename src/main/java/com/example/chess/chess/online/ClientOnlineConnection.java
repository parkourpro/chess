package com.example.chess.chess.online;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.chess.chess.online.packets.Packet;
import com.example.chess.chess.piece.Side;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class ClientOnlineConnection implements ChessOnlineConnection
{
    private static final int DEFAULT_PORT = 19_009;
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Packet.class, new PacketAdapter())
            .create();

    private final Socket socket;
    private final BufferedReader inputStream;
    private final OutputStreamWriter outputStream;

    public static ClientOnlineConnection of(Socket socket, final Consumer<Packet> packetProcessor) throws Exception
    {
        return new ClientOnlineConnection(socket, socket.getInputStream(), socket.getOutputStream(), packetProcessor);
    }

    public static ClientOnlineConnection connect(String ipAddressWithPort, final Consumer<Packet> packetProcessor) throws Exception
    {
        ChessServerAddress chessServerAddress = ChessServerAddress.of(ipAddressWithPort);

        Socket socket = new Socket(chessServerAddress.getIpAddress(), chessServerAddress.getPort());

        return new ClientOnlineConnection(socket, socket.getInputStream(), socket.getOutputStream(), packetProcessor);
    }
    
    private ClientOnlineConnection(Socket socket, InputStream inputStream, OutputStream outputStream, final Consumer<Packet> packetProcessor)
    {
        this.socket = socket;
        this.inputStream = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        this.outputStream = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        processReceivedMessages(packetProcessor);

    }

    public void sendMessage(Packet packet)
    {
        try
        {
            String json = GSON.toJson(packet);
            System.out.println("Json to send: " + json);
            this.outputStream.write(json + System.lineSeparator());
            this.outputStream.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Side getChessSide()
    {
        return Side.BLACK;
    }

    private void processReceivedMessages(Consumer<Packet> consumer)
    {
        var thread = new Thread(() ->
        {
            while (true)
            {
                consumer.accept(awaitMessage());
            }
        });
        thread.start();
    }

    private Packet awaitMessage()
    {
        try
        {
            return GSON.fromJson(this.inputStream.readLine(), Packet.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static final class ChessServerAddress
    {
        private final String ipAddress;
        private final int port;

        public static ChessServerAddress of(String ipAddressWithPort)
        {
            String[] ipAndPort;
            if (ipAddressWithPort.contains(":"))
            {
                ipAndPort = ipAddressWithPort.split(":");
            }
            else
            {
                ipAndPort = new String[] {ipAddressWithPort, String.valueOf(DEFAULT_PORT)};
            }

            return new ChessServerAddress(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
        }

        private ChessServerAddress(final String ipAddress, int port)
        {
            this.ipAddress = ipAddress;
            this.port = port;
        }

        public String getIpAddress()
        {
            return ipAddress;
        }

        public int getPort()
        {
            return port;
        }
    }
}
