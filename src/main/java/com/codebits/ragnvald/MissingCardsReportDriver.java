package com.codebits.ragnvald;

import com.codebits.ragnvald.repository.InMemoryInventoryRepository;
import com.codebits.ragnvald.repository.InMemoryPokemonSetRepository;
import com.codebits.ragnvald.repository.InMemoryPokemonCardRepository;
import com.codebits.ragnvald.bean.FullSetAdapter;
import com.codebits.ragnvald.bean.InventoryReader;
import com.codebits.ragnvald.bean.MissingCardCounter;
import com.codebits.ragnvald.bean.PokemonCardReader;
import com.codebits.ragnvald.bean.PokemonSetsReader;
import com.codebits.ragnvald.domain.PokemonCard;
import com.codebits.ragnvald.domain.PokemonSet;
import com.codebits.ragnvald.repository.InventoryRepository;
import com.codebits.ragnvald.repository.PokemonCardRepository;
import com.codebits.ragnvald.repository.PokemonSetRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.codebits.ragnvald.*"})
public class MissingCardsReportDriver {

    public static void main(final String[] args) throws IOException {
        
        Logger.getRootLogger().setLevel(Level.INFO);
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InventoryReader.class);
        ctx.register(PokemonCardReader.class);
        ctx.register(PokemonSetsReader.class);
        ctx.register(InMemoryInventoryRepository.class);
        ctx.register(InMemoryPokemonCardRepository.class);
        ctx.register(InMemoryPokemonSetRepository.class);
        ctx.register(FullSetAdapter.class);
        ctx.register(MissingCardCounter.class);
        ctx.refresh();

        MissingCardsReportDriver driver = new MissingCardsReportDriver();
        driver.process(ctx);
    }

    private void process(ApplicationContext ctx) throws FileNotFoundException {
        InventoryRepository inventoryRepository = ctx.getBean(InMemoryInventoryRepository.class);
        PokemonCardRepository pokemonCardRepository = ctx.getBean(InMemoryPokemonCardRepository.class);
        PokemonSetRepository pokemonSetRepository = ctx.getBean(InMemoryPokemonSetRepository.class);

        // instantiate reader objects which reads the csv files.
        PokemonSetsReader pokemonSetsReader = ctx.getBean(PokemonSetsReader.class);
        PokemonCardReader pokemonCardReader = ctx.getBean(PokemonCardReader.class);
        InventoryReader inventoryReader = ctx.getBean(InventoryReader.class);

        FullSetAdapter fullSetAdapter = ctx.getBean(FullSetAdapter.class);
        MissingCardCounter missingCardCounter = ctx.getBean(MissingCardCounter.class);
        
        System.out.println("pokemonSetRepository: " + pokemonSetRepository.count());
        System.out.println("pokemonCardRepository: " + pokemonCardRepository.count());
        System.out.println("inventoryRepository: " + inventoryRepository.count());

        int cardsInInventory = inventoryRepository.countCards().intValue();
        //int cardsInMasterSets = 0;
        //int cardsInFullSets = 0;
        for (PokemonSet set : pokemonSetRepository.findAll()) {
            List<PokemonCard> fullSet = fullSetAdapter.getFullSet(set.getRootName());
            if (fullSet == null) {
                continue;
            }
            //cardsInFullSets += fullSet.size();
            //cardsInMasterSets += pokemonCardRepository.countByPokemonSetRootName(set.getRootName());
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Pokemon Card Report</h1>");
        sb.append("<table><tr>");
        sb.append(String.format("<td>Cards in Inventory: %,d</td>", cardsInInventory));
        sb.append("</tr></table>");
        sb.append("<table border=\"1\"><tr><th>Icon</th><th width=\"100px\">Set</th><th>Missing Count</th><th>Missing Cards</th></tr>");
        for (PokemonSet set : pokemonSetRepository.findAll()) {
            if (set.getNumber() != -1) {
                sb.append(String.format("<tr><td valign=\"top\"><img src='src/main/resources/static/images/%03d.png' height='20' width='20'/></td>", set.getNumber()));
                sb.append(String.format("<td valign=\"top\">%03d:", set.getNumber()));
                if (set.getMaster()) {
                    sb.append("MASTER ");
                }
                sb.append(String.format("%s</td>", set.getName()));
                sb.append("<td valign=\"top\" style=\"font-size: 10px\">");
                sb.append(String.format("%d / %d", missingCardCounter.countMissingCards(set.getRootName()), missingCardCounter.fullSetSize(set.getRootName())));
                sb.append("</td>");
                sb.append("<td valign=\"top\" style=\"font-size: 12px\">");
                if (set.getMaster()) {
                    sb.append(missingCardCounter.missingCards(set.getRootName()));
                } else {
                    sb.append(String.format("<table><tr><td valign=\"top\">Missing</td><td>%s</td></tr><tr><td valign=\"top\">Owned</td><td>%s</td></tr></table>", missingCardCounter.missingCards(set.getRootName()), missingCardCounter.ownedCards(set.getRootName())));
                }
                sb.append("</td></tr>");
            }
        }
        sb.append("</table></table>");
        
        PrintWriter writer = new PrintWriter("missing.html");
        writer.print(sb.toString());
        writer.close();
    }

}