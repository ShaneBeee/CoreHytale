package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.assetstore.AssetRegistry;
import com.hypixel.hytale.assetstore.AssetStore;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AssetsCommand extends AbstractCommandCollection {

    private final HytaleLogger.Api logger = HytaleLogger.getLogger().at(Level.INFO);

    public AssetsCommand() {
        super("assetstore", "Lists all assets in a registry.");
        requirePermission("core.commands.assetstore");

        Map<String,AssetStore<?,?,?>> storeMap = new HashMap<>();

        AssetRegistry.getStoreMap().forEach(((aClass, assetStore) -> {
            storeMap.put(assetStore.getAssetClass().getSimpleName(), assetStore);
        }));

        storeMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry ->
            subtest(entry.getKey(), entry.getValue()));
    }

    private void subtest(String name, AssetStore assetStore) {
        this.addSubCommand(new AbstractCommand(name, "Prints the " + name + " assets to console.") {
            @NonNullDecl
            @Override
            protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
                return CompletableFuture.runAsync(() -> printAssetMapTest(assetStore));
            }
        });
    }

    private void printAssetMapTest(AssetStore assetStore) {
        Map<String, ?> assetMap = assetStore.getAssetMap().getAssetMap();

        AtomicInteger count = new AtomicInteger();

        assetMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            AssetsCommand.this.logger.log("- " + entry.getKey());
            count.getAndIncrement();
        });
        AssetsCommand.this.logger.log("Total: " + count.get());
    }

}
