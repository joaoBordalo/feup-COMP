package src;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import gen.*;


import java.io.IOException;


/**
 * Created by Lycantropus on 04-05-2015.
 */
public class bgl2sdl {

    public static void  main(String[] args) throws IOException {


        ANTLRInputStream input = new ANTLRInputStream(System.in);

        XMLLexer lexer = new XMLLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        XMLParser parser = new XMLParser(tokens);

        ParseTree tree = parser.startpoint();

        System.out.println(tree.toStringTree(parser));









    }
}
